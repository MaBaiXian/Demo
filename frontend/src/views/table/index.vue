<template>
  <div class="app-container">

    <el-form ref="queryForm" :model="queryParams" size="small" :inline="true" label-width="68px">

      <el-form-item label="标题" prop="supplierName">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入报修标题"
          clearable
        />
      </el-form-item>

      <el-form-item label="申请时间">
        <el-date-picker
          v-model="queryParams.applicationTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        />
      </el-form-item>
      <el-form-item label="处理状态" prop="status">
        <!--        <el-input
          v-model="queryParams.status"
          placeholder="请输入业务员名称"
          clearable
          clearable
        />-->
        <el-select v-model="queryParams.status">
          <el-option value="0" label="未处理">未处理</el-option>
          <el-option value="1" label="已处理">已处理</el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="标题" width="100">
        <template slot-scope="scope">
          {{ scope.row.title }}
        </template>
      </el-table-column>
      <el-table-column label="学号" width="100" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.applicant }}</span>
        </template>
      </el-table-column>
      <el-table-column label="内容" width="360" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.content }}</span>
        </template>
      </el-table-column>
      <el-table-column label="图片" width="200" prop="imageUri" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.imageUri" min-width="290" height="70">
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="Status" width="150" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 1" type="success">已处理</el-tag>
          <el-tag v-else-if="scope.row.status === 0" type="warning">未处理</el-tag>
          <span v-else>{{ scope.row.status }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="createTime" label="时间" width="300">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.applicationTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['scm:contract:edit']"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            v-hasPermi="['scm:contract:remove']"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="deleteRepair(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>

    </el-table>

    <!-- 分页 current-page、page-size、total要绑定数据 @current-change表示当前页变化时请求getList方法,会把current-page的值传到getList方法中的page参数-->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      background
      layout="prev, pager, next"
      @current-change="handleCurrentChange"
    />

    <!-- 添加或修改采购合同对话框 -->
    <el-dialog title="修改报修" :visible.sync="open" width="70%" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">

        <el-form-item label="报修标题" prop="title" style="flex: 1; margin-right: 20px;">
          <el-input v-model="form.title" placeholder="请输入标题" disabled="disabled" />
        </el-form-item>

        <el-form-item label="报修学生" prop="student" style="flex: 1; margin-right: 20px;">
          <el-input v-model="form.student" placeholder="请输入学生姓名" disabled="disabled" />
        </el-form-item>

        <el-form-item label="报修内容" prop="content" style="flex: 1; margin-right: 20px;">
          <el-input v-model="form.content" placeholder="请输入报修内容" disabled="disabled" />
        </el-form-item>

        <el-form-item label="报修照片" prop="imageUri" style="flex: 1; margin-right: 20px;">
          <img :src="form.imageUri" min-width="120">
        </el-form-item>

        <el-form-item label="报修时间" prop="applicationTime" style="flex: 1; margin-right: 20px;">
          <el-input v-model="form.applicationTime" disabled="disabled" />

        </el-form-item>

        <el-form-item label="处理状态" prop="status" style="flex: 1; margin-right: 20px;">
          <el-select v-model="form.status" placeholder="请选择方式">
            <el-option
              v-for="dict in selectStatus"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>

    </el-dialog>

  </div>
</template>

<script>
import { deleteRepair, getList, getRepairInfo } from '@/api/table'
import { updateRepair } from '@/api/article'

export default {
  filters: {
    statusFilter(status) {
      debugger
      let rt = ''
      if (status === 0) {
        rt = '未处理'
      }
      if (status === 1) {
        rt = '已处理'
      }
      return rt
    }
  },
  data() {
    return {
      list: null,
      page: 1,
      limit: 10,
      total: 0, // 总记录数
      listLoading: true,
      queryParams: {
        title: '',
        applicationTime: '',
        status: '',
        beginTime: '',
        endTime: ''
      },
      form: {},
      open: false,
      selectStatus: [
        { value: 0,
          label: '未处理'
        },
        {
          value: 1,
          label: '已处理'
        }
      ]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getList(this.page, this.limit, this.queryParams).then(response => {
        this.list = response.data.records
        this.total = response.data.total

        this.listLoading = false
      })
    },
    handleCurrentChange(newPage) {
      this.page = newPage
      // 这里执行其他的分页逻辑
      this.listLoading = true
      getList(this.page, this.limit, this.queryParams).then(response => {
        this.list = response.data.records
        this.total = response.data.total
        this.listLoading = false
      })
    },
    handleQuery() {
      if (this.queryParams.applicationTime !== '' && this.queryParams.applicationTime != null) {
        this.queryParams.beginTime = this.queryParams.applicationTime[0]
        this.queryParams.endTime = this.queryParams.applicationTime[1]
        this.queryParams.applicationTime = null
      }
      getList(this.page, this.limit, this.queryParams).then(response => {
        this.list = response.data.records
        this.total = response.data.total
        this.listLoading = false
      })
    },
    resetQuery() {
      this.queryParams.title = null
      this.queryParams.applicationTime = null
      this.queryParams.status = null
      this.queryParams.beginTime = null
      this.queryParams.endTime = null
      getList(this.page, this.limit, this.queryParams).then(response => {
        this.list = response.data.records
        this.total = response.data.total
        this.listLoading = false
      })
    },
    handleUpdate(row) {
      getRepairInfo(row.id).then(response => {
        this.form = response.data
        this.open = true
      })
    },
    submitForm() {
      var upForm = this.form
      upForm.applicationTime = null
      updateRepair(upForm).then(response => {
        debugger
        this.$message({
          message: response.data,
          type: 'success'
        })
        this.cancel()
      })
    },
    cancel() {
      this.form.title = null
      this.form.student = null
      this.form.content = null
      this.form.imageUri = null
      this.form.applicationTime = null
      this.open = false
    },
    deleteRepair(row) {
      deleteRepair(row.id).then(response => {
        this.$message({
          message: response.data,
          type: 'success'
        })
        this.handleQuery()
      })
    }

  }
}
</script>
