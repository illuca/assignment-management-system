<template>
  <div class="student-homework-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-book"></i> 作业列表
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
          <el-table-column label="教师" prop="teacherName"/>
          <el-table-column label="作业标题" prop="homeworkTitle"/>
          <el-table-column label="作业内容" prop="homeworkContent" width="200px"/>
          <el-table-column align="center" label="操作" width="200px">
            <template slot-scope="scope">

              <el-button @click="editStudentHomework(scope.row.homeworkId)"
                         size="mini" type="success">
                提交作业
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


          <div class="file-upload">

            <!--发送homeworkId，后台存入 student_homework_file-->
            <uploader
                browse_button="browse_button"
                :url="server_config.url+'/File/?homeworkId='+entityForm.homeworkId+'&userId='+this.$store.state.status.userId"
                :multi_selection="false"
                :FilesAdded="filesAdded"
                :filters="{
                      mime_types : [
                        { title : 'Image files', extensions : 'jpg,gif,png' },
                        { title : 'Zip files', extensions : 'zip' }
                      ],
                      max_file_size : '400kb'
                    }"
                @inputUploader="inputUploader"
            />

            <el-button id="browse_button" type="primary">选择文件</el-button>
            <span v-for="file in files">{{ file.name }}</span>
            <el-button type="danger" @click="uploadStart()">开始上传</el-button>

            <el-dialog title="正在上传" :visible.sync="dialogTableVisible">
              <el-progress v-if="files.length>0" :text-inside="true"
                           :stroke-width="20"
                           :percentage="files[0].percent"></el-progress>
            </el-dialog>
            <br/>
            <br/>
            <el-tag type="warning">只允许上传图片和zip文件, 最大只能上传400kb的文件</el-tag>

          </div>

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
import * as homeworkApi from "@/api/student/homework";
import Uploader from "@/components/Uploader"

export default {
  name: "StudentHomework",
  computed: {
    status() {
      return this.files.length > 0 ? this.files[0].status : null;
    }
  },
  watch: {
    status() {
      if (this.status === 5) {
        this.$confirm('文件上传成功', '提示', {
          confirmButtonText: '确定',
          type: 'warning'
        }).then(() => {
          this.dialogTableVisible = false;
        });
      }
    }
  },
  data() {
    return {
      // 文件上传
      server_config: this.global.server_config,
      files: [],
      up: {},
      dialogTableVisible: false,

      // 文本
      queryForm: {
        homeworkId: "",
        homeworkTitle: ""
      },
      entityForm: {},
      tableData: [],
      pageSize: homeworkApi.pageSize,
      pageCount: 1,
      pageIndex: 1,
      editing: false
    };
  },
  components: {
    Uploader
  },
  methods: {
    filesAdded(up) {
      if (up.files.length > 1) {
        up.removeFile(up.files[0])
      }
    },
    inputUploader(up) {
      this.up = up;
      this.files = up.files;
    },
    uploadStart() {
      this.dialogTableVisible = true;
      this.up.start();
    },

    // orig
    query() {
      homeworkApi.getPageCount(this.queryForm.homeworkId, this.queryForm.homeworkTitle).then(res => {
        this.pageCount = res;
        this.pageIndex = 1;
        this.getPage(1);
      });
    },
    getPage(pageIndex) {
      homeworkApi.getPage(pageIndex, this.queryForm.homeworkId, this.queryForm.homeworkTitle).then(res => {
        this.tableData = res;
      });
    },
    editStudentHomework(homeworkId) {
      homeworkApi.getHomework(homeworkId).then(res => {
        this.entityForm = res;
        this.editing = true;
      })
    },
    save() {
      homeworkApi.submitHomework(this.entityForm).then(() => {
        this.$message.success("成功");
        this.getPage(this.pageIndex);
        this.editing = false;
      });
    }
  },
  created() {
    this.query();
  }
}
</script>

<style scoped>

</style>