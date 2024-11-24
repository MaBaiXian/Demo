<template>
  <div class="dashboard-container">
    <div class="dashboard-text">
      <profile />
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import profile from '@/views/profile/index.vue'

export default {
  name: 'Dashboard',
  components: {
    profile
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

// <style lang="scss" scoped>
// .dashboard {
//   &-container {
//     margin: 30px;
//   }
//   &-text {
//     font-size: 30px;
//     line-height: 46px;
//   }
// }
// </style>
