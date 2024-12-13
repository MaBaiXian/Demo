
<template>
  <div class="tab-container">
    <!-- 直接显示 Feepay 组件 -->
    <Feepay @create="showCreatedTimes" />
  </div>
</template>

<script>
import Feepay from './components/Feepay' // 引入Feepay组件

export default {
  name: 'FeePaymentTab',
  components: {
    Feepay // 注册Feepay组件
    // eslint-disable-next-line vue/no-unused-components
  },
  data() {
    return {
      createdTimes: 0
    }
  },
  methods: {
    showCreatedTimes() {
      this.createdTimes = this.createdTimes + 1
    }
  }
}
</script>

<style scoped>
  .tab-container {
    margin: 30px;
  }
</style>

