<template>
  <div>
    <!-- 过滤和操作容器 -->
    <div class="filter-container">
      <!-- 添加公告按钮 -->
      <el-button type="primary" size="medium" @click="showAddDialog">添加公告</el-button>
      <!-- 排序按钮 -->
      <el-button size="medium" @click="sortByDate = !sortByDate; sortNoticesByDate()">按时间排序</el-button>
      <!-- 搜索栏 -->
      <div class="search-bar-container">
        <el-input v-model="filterContent" placeholder="搜索公告..." size="medium" />
      </div>
      <!-- 搜索按钮 -->
      <el-button type="primary" size="medium" @click="filterNotices">搜索</el-button>
    </div>

    <!-- 列表间隔 -->
    <div class="list-spacing" />

    <!-- 公告列表 -->
    <el-table :data="notices" style="width: 100%">
      <el-table-column prop="id" label="公告ID" width="180" />
      <el-table-column prop="message" label="内容" />
      <el-table-column prop="publisher" label="发布者" />
      <el-table-column prop="time" label="发布时间" />
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="editNotice(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="deleteNotice(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle">
      <el-form :model="currentNotice">
        <el-form-item label="内容">
          <el-input v-model="currentNotice.message" type="textarea" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveNotice">保存</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { updateAnnouncement, createAnnouncement, getAllAnnouncements, deleteAnnouncement } from '@/api/Announcement'

export default {
  data() {
    return {
      notices: [],
      allNotices: [],
      dialogVisible: false,
      dialogTitle: '添加公告',
      currentNotice: {
        id: null,
        message: '',
        publisher: '',
        time: '' // 添加时间属性
      },
      filterContent: '',
      sortByDate: false
    }
  },
  mounted() {
    this.fetchNotices()
  },
  methods: {
    fetchNotices() {
      getAllAnnouncements().then(response => {
        this.notices = response.data
        this.allNotices = this.notices
        console.log(response.Announcements)
      })
    },
    showAddDialog() {
      this.dialogTitle = '添加公告'
      this.currentNotice = { id: null, message: '', publisher: '', time: '' }
      this.dialogVisible = true
    },
    editNotice(notice) {
      this.dialogTitle = '编辑公告'
      this.currentNotice = { ...notice }
      this.dialogVisible = true
    },
    deleteNotice(id) {
      // 调用删除公告的 API 方法
      deleteAnnouncement(id).then(response => {
        if (response.message === 'ok') {
          this.$message({
            message: '删除成功',
            type: 'success',
            duration: 5 * 1000
          })
          // 更新本地公告列表
          this.fetchNotices()
        }
      })
    },
    saveNotice() {
      // 检查当前通知对象是否有id属性
      if (this.currentNotice.id) {
        // 如果有id，发送PUT请求更新通知
        updateAnnouncement(this.currentNotice.id, this.currentNotice).then(response => {
          if (response.message === 'ok') {
            // 更新成功，显示成功消息
            this.$message({
              message: '更新成功',
              type: 'success',
              duration: 5 * 1000
            })
            // 更新本地通知列表
            this.dialogVisible = false
            this.fetchNotices()
          }
        })
      } else {
        // 如果没有id，发送POST请求创建新通知
        createAnnouncement(this.currentNotice).then(response => {
          if (response.message === 'ok') {
            // 创建成功，显示成功消息
            this.$message({
              message: '创建成功',
              type: 'success',
              duration: 5 * 1000
            })
            // 更新本地通知列表
            this.dialogVisible = false
            this.fetchNotices()
          }
        })
      }
    },
    filterNotices() {
      if (this.filterContent) {
        this.notices = this.allNotices.filter(notice =>
          (notice.message.toLowerCase().includes(this.filterContent.toLowerCase())) ||
          (notice.publisher.toLowerCase().includes(this.filterContent.toLowerCase()))
        )
      } else {
        this.notices = this.allNotices
      }
    },
    sortNoticesByDate() {
      this.notices.sort((a, b) => {
        if (this.sortByDate) {
          return new Date(b.time) - new Date(a.time)
        } else {
          return new Date(a.time) - new Date(b.time)
        }
      })
    }
  }
}
</script>

<style scoped>
.filter-container {
  margin-top: 20px; /* 与顶部的边距 */
  padding: 10px; /* 内边距 */
  display: flex;
  align-items: center;
  gap: 10px; /* 按钮间隔 */
  background-color: #fff; /* 背景颜色 */
}

.list-spacing {
  height: 20px; /* 列表间隔 */
  margin-top: 20px; /* 与顶部的边距 */
  padding: 10px; /* 内边距 */
}

.search-bar-container {
  width: 20%; /* 搜索栏宽度 */
  margin-left: auto; /* 靠右对齐 */
}
</style>
