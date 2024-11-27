<template>
  <el-form>
    <el-form-item label="姓名">
      <el-input v-model.trim="user.name" readonly />
    </el-form-item>
    <el-form-item label="性别">
      <el-input v-model.trim="user.gender" readonly />
    </el-form-item>
    <el-form-item label="宿舍">
      <el-input v-model.trim="user.dorm" readonly />
    </el-form-item>
    <el-form-item label="年级专业">
      <el-input v-model.trim="user.major" readonly />
    </el-form-item>
    <!-- <el-form-item label="密码">
      <el-input v-model.trim="user.password" />
    </el-form-item> -->
    <!-- <el-form-item>
      <el-button type="primary" @click="submit">提交</el-button>
    </el-form-item> -->
  </el-form>
</template>

<script>
export default {
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          password: ''
        }
      }
    }
  },
  created() {
    console.log(this.user)
  },
  methods: {
    submit() {
      this.$message({
        message: '用户信息已成功更新',
        type: 'success',
        duration: 5 * 1000
      })
    }
  }
}
</script>
