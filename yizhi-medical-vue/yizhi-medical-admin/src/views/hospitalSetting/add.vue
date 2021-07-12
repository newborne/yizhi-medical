<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="医院编号">
        <el-input v-model="hospitalSetting.hospitalCode" />
      </el-form-item>
      <el-form-item label="医院名称">
        <el-input v-model="hospitalSetting.hospitalName" />
      </el-form-item>
      <el-form-item label="接口地址">
        <el-input v-model="hospitalSetting.apiUrl" />
      </el-form-item>
      <el-form-item label="联系人姓名">
        <el-input v-model="hospitalSetting.linkmanName" />
      </el-form-item>
      <el-form-item label="联系人手机">
        <el-input v-model="hospitalSetting.linkmanPhone" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {
  getHospitalSetting,
  saveHospitalSetting,
  updateHospitalSetting
} from '@/api/hospitalSetting'

export default {
  data() {
    return {
      hospitalSetting: {}
    }
  },
  created() {
    if (this.$route.params && this.$route.params.id) {
      const id = this.$route.params.id
      this.getHospitalSetting(id)
    } else {
      // 表单数据清空
      this.hospitalSetting = {}
    }
  },
  mounted() {},
  methods: {
    // 根据id查询
    getHospitalSetting(id) {
      getHospitalSetting(id).then(response => {
        this.hospitalSetting = response.data
      })
    },
    // 添加
    save() {
      saveHospitalSetting(this.hospitalSetting).then(response => {
        // 提示
        this.$message({
          type: 'success',
          message: '添加成功!'
        })
        // 跳转列表页面，使用路由跳转方式实现
        this.$router.push({ path: '/hospitalSetting/list' })
      })
    },
    // 修改
    update() {
      updateHospitalSetting(this.hospitalSetting).then(response => {
        // 提示
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
        // 跳转列表页面，使用路由跳转方式实现
        this.$router.push({ path: '/hospSet/list' })
      })
    },
    saveOrUpdate() {
      // 判断添加还是修改
      if (!this.hospitalSetting.id) {
        // 没有id，做添加
        this.save()
      } else {
        // 修改
        this.update()
      }
    }
  }
}
</script>

<style scoped lang="less"></style>
