<template>
  <div class="student-submitted-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-check"></i> 已提交的作业
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <div class="query-form">
        <el-row :gutter="20">
          <el-col :offset="15" :span="3">
            <el-input @keyup.enter.native="query"
                      onkeyup="value=value.replace(/[^\d]/g,'')"
                      placeholder="作业编号" v-model="queryForm.homeworkId"/>
          </el-col>
          <el-col :span="3">
            <el-input @keyup.enter.native="query" placeholder="作业标题"
                      v-model="queryForm.homeworkTitle"/>
          </el-col>
          <el-col :span="3">
            <el-button @click="query" icon="el-icon-search" type="primary">搜索
            </el-button>
          </el-col>
        </el-row>
      </div>

      <div>
        <p></p>
      </div>

      <el-row justify="center" type="flex">
        <el-pagination
            :current-page.sync="pageIndex"
            :page-size="pageSize"
            :total="pageSize * pageCount"
            @current-change="getPage"
            background
            layout="prev, pager, next"
        >
        </el-pagination>
      </el-row>

      <div>
        <p></p>
      </div>

      <div class="table">
        <el-table :data="tableData" stripe>
          <el-table-column label="作业编号" prop="homeworkId"/>
          <el-table-column label="作业标题" prop="homeworkTitle"/>
          <el-table-column label="作业内容" prop="homeworkContent"/>
          <el-table-column label="提交的标题" prop="title"/>
          <el-table-column label="提交的内容" prop="content"/>
          <el-table-column align="center" label="操作" width="200px">
            <template slot-scope="scope">
              <el-button @click="editStudentHomework(scope.row.homeworkId)"
                         size="mini"
                         type="success">
                编辑作业
              </el-button>
              <el-button
                  @click="downloadFile(scope.row.homeworkId)"
                  size="mini"
                  type="primary">
                下载作业
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <el-dialog :visible.sync="editing" title="编辑" width="50%">
        <el-form :model="entityForm" label-width="82px" ref="form">
          <el-form-item label="作业编号">
            <el-input disabled type="number"
                      v-model="entityForm.homeworkId"></el-input>
          </el-form-item>
          <el-form-item label="作业标题">
            <el-input disabled type="text"
                      v-model="entityForm.homeworkTitle"></el-input>
          </el-form-item>
          <el-form-item label="作业内容">
            <el-input disabled type="textarea"
                      v-model="entityForm.homeworkContent"></el-input>
          </el-form-item>
          <el-form-item label="提交的标题">
            <el-input type="text" v-model="entityForm.title"></el-input>
          </el-form-item>
          <el-form-item label="提交的内容">
            <el-input type="textarea" v-model="entityForm.content"></el-input>
          </el-form-item>
        </el-form>
        <span class="dialog-footer" slot="footer">
                <el-button @click="save" type="primary">确 定</el-button>
                <el-button @click="editing = false">取 消</el-button>
            </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import * as submittedApi from "@/api/student/submitted";
import axios from 'axios'
import Config from '@/common/config'

export default {
  name: "StudentSubmitted",
  data() {
    return {
      queryForm: {
        homeworkId: "",
        homeworkTitle: ""
      },
      entityForm: {},
      tableData: [],
      pageSize: submittedApi.pageSize,
      pageCount: 1,
      pageIndex: 1,
      editing: false
    };
  },
  methods: {
    downloadFile(homeworkId) {
      let studentId = this.$store.state.status.userId
      const axiosInstance = axios.create({
        baseURL: Config.serverUrl,
        timeout: 30000,
        withCredentials: true
      });

      let axiosConfig = {
        headers: {
          'Content-Type': 'application/json; application/octet-stream'
        },
        responseType: 'blob'
      }

      axiosInstance.get(
          '/File/download?studentId=' + studentId + '&homeworkId=' + homeworkId,
          axiosConfig)
          .then((response) => {
            let fileURL = window.URL.createObjectURL(new Blob([response.data], {
              type: 'application/zip'
            }));

            let fileLink = document.createElement('a');

            fileLink.href = fileURL;
            // fileLink.setAttribute('download', 'file.pdf');
            document.body.appendChild(fileLink);

            fileLink.click();
            fileLink.remove();
          });
    },

    query() {
      submittedApi.getPageCount(this.queryForm.homeworkId, this.queryForm.homeworkTitle).then(res => {
        this.pageCount = res;
        this.pageIndex = 1;
        this.getPage(1);
      });
    }
    ,
    getPage(pageIndex) {
      submittedApi.getPage(pageIndex, this.queryForm.homeworkId, this.queryForm.homeworkTitle).then(res => {
        this.tableData = res;
      });
    }
    ,
    editStudentHomework(homeworkId) {
      submittedApi.getStudentHomework(homeworkId).then(res => {
        this.entityForm = res;
        this.editing = true;
      })
    }
    ,
    save() {
      submittedApi.updateStudentHomework(this.entityForm).then(() => {
        this.$message.success("成功");
        this.getPage(this.pageIndex);
        this.editing = false;
      });
    }
  },
  created() {
    this.query();
  }
};
</script>

<style scoped>

</style>