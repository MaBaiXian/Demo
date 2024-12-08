<template>
  <div>
    <!-- 过滤和操作容器 -->
    <div class="filter-container">
      <!-- 添加人员按钮 -->
      <el-button type="primary" size="medium" @click="showAddDialog">添加人员</el-button>
      <!-- 搜索栏 -->
      <div class="search-bar-container">
        <el-input v-model="filterContent" placeholder="搜索人员..." size="medium" />
      </div>
      <!-- 搜索按钮 -->
      <el-button type="primary" size="medium" @click="filterPersonnel">搜索</el-button>
    </div>

    <!-- 列表间隔 -->
    <div class="list-spacing" />

    <!-- 人员列表 -->
    <el-table :data="adminList" style="width: 100%">
      <el-table-column prop="userId" label="ID" width="180" />
      <el-table-column prop="username" label="姓名" />
      <el-table-column prop="roles" label="权限角色" />
      <el-table-column prop="major" label="职能" />
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="viewDetails(scope.row)">查看详情</el-button>
          <el-button size="mini" @click="editPerson(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="deletePerson(scope.row.userId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页容器 -->
    <div class="pagination-container">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="totalAdmin"
        @current-change="handleCurrentChange"
      />
    </div>

    <!-- 对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle">
      <el-form ref="adminForm" :model="currentAdmin" :rules="rules">
        <el-form-item label="姓名" prop="username">
          <el-input v-model="currentAdmin.username" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="currentAdmin.gender">
            <el-radio :label="'男'">男</el-radio>
            <el-radio :label="'女'">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="权限角色" prop="roles">
          <el-radio-group v-model="currentAdmin.roles">
            <el-radio :label="'SysAdmin'">SysAdmin</el-radio>
            <el-radio :label="'DormAdmin'">DormAdmin</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="职能" prop="major">
          <el-input v-model="currentAdmin.major" />
        </el-form-item>
        <el-form-item label="归属单位" prop="dorm">
          <el-input v-model="currentAdmin.dorm" />
        </el-form-item>
        <el-form-item label="账户密码" prop="password">
          <el-input
            v-model="currentAdmin.password"
            type="password"
            show-password
          />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancelDialog">取消</el-button>
        <el-button type="primary" @click="savePerson">保存</el-button>
      </span>
    </el-dialog>

    <!-- 人员详情页 -->
    <el-dialog :visible.sync="detailsDialogVisible" title="人员详情">
      <el-form label-width="100px">
        <div class="avatar-container">
          <img :src="currentAdmin.avatar" alt="头像" class="avatar">
        </div>
        <el-form-item label="姓名">：{{ currentAdmin.username }}</el-form-item>
        <el-form-item label="性别">：{{ currentAdmin.gender }}</el-form-item>
        <el-form-item label="权限角色">：{{ currentAdmin.roles }}</el-form-item>
        <el-form-item label="职能">：{{ currentAdmin.major }}</el-form-item>
        <el-form-item label="归属部门">：{{ currentAdmin.dorm }}</el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="detailsDialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import { getAdminList, createAdmin, updateAdmin, deleteAdmin } from '@/api/AdminManage'

export default {
  data() {
    // 密码验证规则
    const validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else if (value.length < 6) {
        callback(new Error('密码长度必须大于等于6位'))
      } else {
        callback()
      }
    }

    return {
      adminList: [],
      allAdminList: [],
      dialogVisible: false,
      dialogTitle: '添加人员',
      detailsDialogVisible: false,
      currentAdmin: {
        userId: null,
        username: '',
        roles: '',
        major: '',
        gender: '',
        dorm: '',
        avatar: ''
      },
      filterContent: '',
      currentPage: 1,
      totalAdmin: 0,
      rules: {
        username: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { validator: validatePassword, trigger: 'blur' }
        ],
        roles: [
          { required: true, message: '请选择权限角色', trigger: 'change' }
        ],
        major: [
          { required: true, message: '请输入职能', trigger: 'blur' }
        ],
        dorm: [
          { required: true, message: '请输入归属单位', trigger: 'blur' }
        ]
      }
    }
  },
  mounted() {
    this.fetchAdmin()
  },
  methods: {
    fetchAdmin() {
      getAdminList().then(response => {
        this.adminList = response.data
        this.allAdminList = [...this.adminList]
        this.totalAdmin = this.adminList.length / 10 + 1
      })
    },
    showAddDialog() {
      this.dialogTitle = '添加人员'
      this.currentAdmin = {
        userId: null,
        username: '',
        roles: '',
        major: '',
        gender: '',
        dorm: '',
        avatar: '',
        password: ''
      }
      this.$nextTick(() => {
        this.$refs.adminForm.clearValidate()
      })
      this.dialogVisible = true
    },
    editPerson(person) {
      this.dialogTitle = '编辑人员'
      this.currentAdmin = { ...person }
      this.dialogVisible = true
    },
    deletePerson(id) {
      this.$confirm('确认删除该管理员吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteAdmin(id).then(response => {
          if (response.message === 'ok') {
            this.$message({
              message: '删除成功',
              type: 'success',
              duration: 5 * 1000
            })
            this.fetchAdmin()
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    savePerson() {
      this.$refs.adminForm.validate(valid => {
        if (valid) {
          if (this.dialogTitle === '添加人员') {
            createAdmin(this.currentAdmin).then(response => {
              if (response.message === 'ok') {
                this.$message({
                  message: '添加成功',
                  type: 'success',
                  duration: 5 * 1000
                })
                this.dialogVisible = false
                this.fetchAdmin()
              }
            })
          } else {
            updateAdmin(this.currentAdmin.userId, this.currentAdmin).then(response => {
              if (response.message === 'ok') {
                this.$message({
                  message: '更新成功',
                  type: 'success',
                  duration: 5 * 1000
                })
                this.dialogVisible = false
                this.fetchAdmin()
              }
            })
          }
        } else {
          return false
        }
      })
    },
    cancelDialog() {
      this.$refs.adminForm.resetFields()
      this.dialogVisible = false
    },
    filterPersonnel() {
      if (this.filterContent) {
        this.adminList = this.allAdminList.filter(admin => {
          return (admin.username.toLowerCase().includes(this.filterContent.toLowerCase())) ||
                 (admin.roles.toLowerCase().includes(this.filterContent.toLowerCase())) ||
                 (admin.major.toLowerCase().includes(this.filterContent.toLowerCase())) ||
                 (admin.dorm.toLowerCase().includes(this.filterContent.toLowerCase()))
        })
      } else {
        this.adminList = this.allAdminList
      }
    },
    handleSizeChange(newSize) {
      this.fetchAdmin(newSize)
    },
    handleCurrentChange(newPage) {
      this.currentPage = newPage
      this.fetchAdmin(newPage)
    },
    viewDetails(person) {
      this.currentAdmin = { ...person }
      this.detailsDialogVisible = true
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

.pagination-container {
  margin-top: auto; /* 将分页容器推向底部 */
  display: flex;
  justify-content: center; /* 水平居中 */
  padding: 20px; /* 添加一些内边距 */
}
.avatar-container {
  width: 100px;
  height: 100px;
  margin: 20px;
}

.avatar {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  object-fit: cover;
}

</style>
