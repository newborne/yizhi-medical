<template>
  <div class="app-container">
    医院设置添加
  </div>
</template>

<script>
import hospitalSetting from '@/api/hospitalSetting'

export default {
  data() {
    return {
      hospitalSettingList: {}
    }
  },
  created() {
    if (this.$route.params && this.$route.params.id) {
      const id = this.$route.params.id
      this.getHospitalSettingList(id)
    } else {
      // 表单数据清空
      this.hospitalSetting = {}
    }
  },
  mounted() {},
  methods: {
    // 根据id查询
    getHospitalSettingList(id) {
      hospitalSetting.getHospitalSettingList(id).then(response => {
        this.hospitalSetting = response.data
      })
    },
    // 添加
    save() {
      hospitalSetting
        .saveHospitalSettingList(this.hospitalSetting)
        .then(response => {
          // 提示
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          // 跳转列表页面，使用路由跳转方式实现
          this.$router.push({ path: '/hospSet/list' })
        })
    },
    // 修改
    update() {
      hospitalSetting
        .updateHospitalSettingList(this.hospitalSetting)
        .then(response => {
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
