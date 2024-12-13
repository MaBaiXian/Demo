<template>
  <div class="createPost-container">

    <el-form
      ref="postForm"
      :model="postForm"
      :rules="rules"
      class="form-container"
    >

      <!-- 添加标题 -->
      <div class="createPost-title">
        <h1>维 修 申 请</h1>
      </div>

      <div class="createPost-main-container">
        <el-row>
          <div class="postInfo-container">
            <el-row>
              <el-col :span="6">
                <el-form-item
                  label-width="60px"
                  label="申请者:"
                  class="postInfo-container-item"
                >

                  <el-input
                    v-model="postForm.applicant"
                    :rows="1"
                    type="text"
                    autosize
                    placeholder="Please enter the content"
                    readonly="readonly"
                  />

                </el-form-item>
              </el-col>

              <el-col :span="6">
                <el-form-item
                  label-width="160px"
                  label="标题:"
                  class="postInfo-container-item"
                >

                  <el-input
                    v-model="postForm.title"
                    :rows="1"
                    type="text"
                    autosize
                    placeholder="Please enter the title"
                  />

                </el-form-item>
              </el-col>

              <el-col :span="6">
                <el-form-item
                  label-width="120px"
                  label="申请时间:"
                  class="postInfo-container-item"
                >
                  <el-date-picker
                    v-model="postForm.applicationTime"
                    type="datetime"
                    format="yyyy-MM-dd HH:mm:ss"
                    placeholder="Select date and time"
                  />
                </el-form-item>
              </el-col>

              <el-col :span="6">
                <el-form-item
                  label-width="90px"
                  label="紧急等级:"
                  class="postInfo-container-item"
                >
                  <el-rate
                    v-model="postForm.importance"
                    :max="5"
                    :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
                    :low-threshold="1"
                    :high-threshold="3"
                    style="display:inline-block"
                  />
                </el-form-item>
              </el-col>
            </el-row>
          </div>
        </el-row>

        <el-form-item
          style="margin-bottom: 40px;"
          label-width="60px"
          label="详情:"
        >
          <el-input
            v-model="postForm.content"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="Please enter the content"
          />
          <span
            v-show="contentShortLength"
            class="word-counter"
          >{{ contentShortLength }}words</span>
        </el-form-item>

        <el-form-item
          prop="image_uri"
          style="margin-bottom: 30px;"
        >
          <Upload v-model="postForm.imageUri" />
        </el-form-item>
        <sticky
          :z-index="10"
          :class-name="'sub-navbar ' + postForm.status"
        >
          <el-button
            v-loading="loading"
            style="margin-left: 500px;"
            type="success"
            @click="submitForm"
          >
            提交申请
          </el-button>
          <el-button
            v-loading="loading"
            style="margin-left:50px"
            type="warning"
            @click="reset"
          >
            重置
          </el-button>
        </sticky>
      </div>
    </el-form>
  </div>

</template>

<script>
import Upload from '@/components/Upload/SingleImage3'
import Sticky from '@/components/Sticky' // 粘性header组件
import { validURL } from '@/utils/validate'
import { saveRepair } from '@/api/article'
import user from '@/store/modules/user'
import { getInfo } from '@/api/user'

const defaultForm = {
  status: 'draft',
  content_short: '', // 文章摘要
  source_uri: '', // 文章外链
  display_time: undefined, // 前台展示时间
  id: undefined,
  platforms: ['a-platform'],
  comment_disabled: false,
  title: '', // 报修标题
  content: '', // 维修内容
  imageUri: '', // 保修图片
  importance: 0, // 重要等级
  applicant: null, // 报修人
  applicantID: null, // 保修人ID
  applicationTime: null // 报修时间
}

export default {
  name: 'ArticleDetail',
  components: { Upload, Sticky },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === '') {
        this.$message({
          message: rule.field + '为必传项',
          type: 'error'
        })
        callback(new Error(rule.field + '为必传项'))
      } else {
        callback()
      }
    }
    const validateSourceUri = (rule, value, callback) => {
      if (value) {
        if (validURL(value)) {
          callback()
        } else {
          this.$message({
            message: '外链url填写不正确',
            type: 'error'
          })
          callback(new Error('外链url填写不正确'))
        }
      } else {
        callback()
      }
    }
    return {
      postForm: Object.assign({}, defaultForm),
      loading: false,
      userListOptions: [],
      rules: {
        image_uri: [{ validator: validateRequire }],
        title: [{ validator: validateRequire }],
        content: [{ validator: validateRequire }],
        source_uri: [{ validator: validateSourceUri, trigger: 'blur' }]
      },
      tempRoute: {}
    }
  },
  computed: {
    contentShortLength() {
      return this.postForm.content_short.length
    },
    displayTime: {
      get() {
        return (+new Date(this.postForm.display_time))
      },
      set(val) {
        this.postForm.display_time = new Date(val)
      }
    }
  },
  created() {
    if (this.isEdit) {
      const id = this.$route.params && this.$route.params.id
      this.fetchData(id)
    }
    this.getUserInfo()
    this.tempRoute = Object.assign({}, this.$route)
  },
  methods: {
    submitForm() {
      console.log(this.postForm)
      this.$refs.postForm.validate(valid => {
        if (valid) {
          this.loading = true
          const data = {
            applicant: this.postForm.applicantID,
            applicationTime: this.postForm.applicationTime,
            importance: this.postForm.importance,
            content: this.postForm.content,
            imageUri: this.postForm.imageUri,
            title: this.postForm.title
          }
          saveRepair(data).then(response => {
            this.$message({
              message: '提交申请成功',
              type: 'success'
            })
            this.reset()
          })
          this.postForm.status = 'published'
          this.loading = false
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },

    getUserInfo() {
      getInfo(user.state.token).then(response => {
        const { data } = response
        console.log('---------->getInfo用token得到userinfo方法', data)
        if (!data) {
          this.$message({
            message: '验证失败，请重新登录',
            type: 'warning'
          })
          return
        }
        this.postForm.applicant = data.username
        this.postForm.applicantID = data.userId
      }).catch(error => {
        this.$message({
          message: error.message,
          type: 'warning'
        })
      })
    },

    reset() {
      this.postForm.applicantID = null
      this.postForm.applicationTime = null
      this.postForm.content = null
      this.postForm.imageUri = ''
      this.postForm.title = null
    }
  }
}
</script>

<style lang="scss" scoped>
@import '~@/styles/mixin.scss';

.createPost-container {
  position: relative;

  .createPost-main-container {
    padding: 40px 45px 20px 50px;

    .postInfo-container {
      position: relative;
      @include clearfix;
      margin-bottom: 10px;

      .postInfo-container-item {
        float: left;
      }
    }
  }

  .word-counter {
    width: 40px;
    position: absolute;
    right: 10px;
    top: 0px;
  }
}

.article-textarea ::v-deep {
  textarea {
    padding-right: 40px;
    resize: none;
    border: none;
    border-radius: 0px;
    border-bottom: 1px solid #bfcbd9;
  }
}

.createPost-title {
  width: 100%;
  /* 确保标题容器占满宽度 */
  text-align: center;
  /* 确保文本在容器内居中 */
  margin-bottom: 20px;
}
</style>

