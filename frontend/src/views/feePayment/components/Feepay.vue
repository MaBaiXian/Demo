<template>
  <div>
    <!-- 使用 el-row 和 el-col 来布局输入框和按钮 -->
    <el-row type="flex" align="middle">
      <el-col :span="16">
        <el-input v-model="studentId" placeholder="请输入学生ID" />
      </el-col>
      <el-col :span="8" style="text-align: right;">
        <el-button type="primary" @click="fetchFee">查询</el-button>
      </el-col>
    </el-row>
    <el-table :data="feeList" border fit hoghlight-current-row style="width: 100%" v-if="feeList.length > 0">
      <el-table-column prop="stuId" label="学生ID" align= "center"/>
      <el-table-column prop="stuName" label="学生姓名" align= "center" />
      <el-table-column prop="className" label="学生班级" align= "center" />
      <el-table-column prop="fee" label="应缴费用" align="center" />
      <el-table-column prop="dormitory" label="宿舍号" align="center" />
    </el-table>
    <div v-else class="no-data">暂无费用信息</div>
  </div>
</template>

<script>
import { getFeeByStudentId } from '@/api/Fee' // 确保引入了Fee.js中的函数
// import { config } from '@vue/test-utils';
// border fit highlight-current-row

export default {
  data() {
    return {
      studentId: '',
      feeList: [],
      loading: false
    }
  },
  methods: {
    fetchFee() {
      if (!this.studentId) {
        this.$message.error('请输入学生ID')
        return
      }
      this.loading = true
      getFeeByStudentId(this.studentId).then(response => {
        console.log(response.data)
        if (response && response.data) {
          // 检查响应数据是否为数组
          if (Array.isArray(response.data)) {
            this.feeList = response.data
          } else {
            // 如果不是数组，将其转换为数组
            this.feeList = [response.data]
          }
        } else {
          this.feeList = []
          this.$message.warning('未找到相关费用信息')
        }
        this.loading = false
      }).catch(() => {
        this.$message.error('查询费用信息失败')
        this.loading = false
      })
    },
    formatFee(row, column, cellValue) {
      // 格式化应缴费用，例如保留两位小数
      return parseFloat(cellValue).toFixed(2)
    }
  }
}
</script>

<style scoped>
.el-table {
  width: 100%;
}
.no-data {
  color: #999;
  text-align: center;
  margin-top: 10px;
}
</style>
