import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: '',
    roles: [],
    gender: '',
    dorm: '',
    major: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_Gender: (state, gender) => {
    state.gender = gender
  },
  SET_Major: (state, major) => {
    state.major = major
  },
  SET_Dorm: (state, dorm) => {
    state.dorm = dorm
  },
  SET_ROLES: (state, roles) => {
    state.roles.push(roles)
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        console.log('----------->login登陆方法', response)
        const { data } = response
        commit('SET_TOKEN', data.token) // 存到vuex中
        setToken(data.token) // 将token存到cookie中
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info获取用户信息
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response
        console.log('---------->getInfo用token得到userinfo方法', data)
        if (!data) {
          return reject('验证失败，请重新登录')
        }

        const { username, avatar, roles, major, dorm, gender } = data

        // roles must be a non-empty array
        if (!roles || roles.length <= 0) {
          reject('信息获取：用户角色权限不能为空！')
        }

        commit('SET_NAME', username)
        commit('SET_AVATAR', avatar)
        commit('SET_ROLES', roles)
        commit('SET_Gender', gender)
        commit('SET_Major', major)
        commit('SET_Dorm', dorm)
        console.log('---------->getInfo方法查看权限roles写入', state.roles)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout 登出
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        console.log('---------->logout登出方法', state)
        removeToken() // must remove  token  first 移除token
        resetRouter() // 重置路由
        commit('RESET_STATE') // 清空vuex
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

