import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login'] // no redirect whitelist 白名单
// 导航守卫
router.beforeEach(async(to, from, next) => {
  // start progress bar
  // 启用加载图标
  NProgress.start()

  // set page title 浏览器标签名
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in 获取token
  const hasToken = getToken()

  if (hasToken) {
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      next({ path: '/' })
      NProgress.done() // 加载图标
    } else {
      const hasRoles = store.getters.roles && store.getters.roles.length > 0 // 是否获取过用户信息
      if (hasRoles) {
        next() // 直接放行
      } else {
        try {
          // 获取权限角色
          const { roles } = await store.dispatch('user/getInfo')

          // 通过角色获取路由
          const accessRoutes = await store.dispatch('permission/generateRoutes', roles)

          // 合并路由
          router.addRoutes(accessRoutes)

          next({ ...to, replace: true }) // 放行
        } catch (error) {
          // remove token and go to login page to re-login 出错需要重置令牌并重新登陆(令牌过期,网络错误等原因)
          await store.dispatch('user/resetToken')
          Message.error(error || 'Has Error')
          next(`/login?redirect=${to.path}`)
          NProgress.done()
        }
      }
    }
  } else {
    /* has no token*/
    // 没有token
    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly 白名单放行
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page. 没有token，没有白名单，去到登录页
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // finish progress bar全局后置钩子
  NProgress.done()
})
