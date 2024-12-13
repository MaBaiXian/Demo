<template>
  <div>
    <el-input v-model="searchId" placeholder="请输入要查询的学生ID" @keyup.enter="searchFee" />
    <el-button @click="searchFee">搜索</el-button>
    <div ref="tableWrapper" class="table-wrapper" style="overflow-y: auto; height: 500px;" @scroll="handleScroll">
      <el-table
        :ref="tableWrapper"
        :data="feeList"
        border
        fit
        :highlight-current-row="highlightId"
        style="width: 100%"
        @scroll="handleScroll"
      >
        <el-table-column prop="stuId" label="学生ID" />
        <el-table-column prop="stuName" label="学生姓名" />
        <el-table-column prop="className" label="学生班级" />
        <el-table-column prop="fee" label="应缴费用" />
        <el-table-column prop="dormitory" label="宿舍号" />
      </el-table>
    </div>
    <div v-if="loading" class="loading-indicator">加载中...</div>
  </div>
</template>

<script>
import { getAllFees } from '@/api/Fee' // 确保引入了Fee.js中的函数
export default {
  data() {
    return {
      feeList: [], // 用于存储费用信息的数组
      loading: false, // 加载状态
      allFetched: false, // 标记是否已获取所有数据
      searchId: '', // 用于绑定输入框的数据
      highlightId: null // 用于存储高亮行的学生ID
    }
  },
  created() {
    this.fetchFees() // 在组件创建时获取费用信息
  },
  methods: {
    fetchFees() {
      if (this.loading || this.allFetched) {
        return
      }

      this.loading = true
      getAllFees().then(response => {
        if (response && response.data) {
        // 检查响应是否为数组
          if (Array.isArray(response.data)) {
            this.feeList = response.data
            if (response.data.length < 10) { // 假设每页10跳数据，这里做简化处理
              this.allFetched = true
            }
          } else {
            // 如果不是数组，将其转换为数组
            this.feeList = [response.data]
          }
        } else {
          this.feeList = []
          this.$message.warning('未找到费用信息')
        }
        this.loading = false
      }).catch(() => {
        this.$message.error('获取费用信息失败')
        this.loading = false
      })
    },
    handleScroll() {
      const { scrollTop, scrollHeight, clientHeight } = this.$refs.tableWrapper
      if (scrollTop + clientHeight >= scrollHeight) {
        this.fetchFees()
      }
    },
    searchFee() {
      const foundIndex = this.feeList.findIndex(item => item.stuId === this.searchId)
      if (foundIndex !== -1) {
        this.highlightId = this.feeList[foundIndex].stuId // 设置高亮行学生ID
        const rowHeight = 50 // 假设每行的高度是50px，根据实际情况调整
        const scrollTop = foundIndex * rowHeight
        this.$refs.tableWrapper.scrollTop = scrollTop
      } else {
        this.$message.warning('未找到相关费用信息')
      }
    }

  }
}
</script>

  <style scoped>
  .table-wrapper {
    position: relative;
    overflow: auto;
    height: 500px;
  }

  .loading-indicator {
    position: absolute;
    bottom: 10;
    width: 100%;
    text-align: center;
  }
  </style>
