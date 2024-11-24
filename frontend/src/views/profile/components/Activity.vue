<template>
  <div class="user-activity">
    <div v-for="(announcement, index) in Announcements" :key="index" class="post">
      <div class="user-block">
        <img class="img-circle" :src="announcement.avatar+avatarPrefix">
        <span class="username text-muted">{{ announcement.publisher }}</span>
        <span class="description">发布时间：{{ announcement.time }}</span>
      </div>
      <!-- <div v-html="announcement.message"></div> -->
      {{ announcement.message }}
    </div>
    <span class="username text-muted">“看看诗和远方”</span>
    <div class="user-images">
      <el-carousel :interval="6000" type="card" height="220px">
        <el-carousel-item v-for="item in carouselImages" :key="item">
          <img :src="item+carouselPrefix" class="image">
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
import { getAnnouncements } from '@/api/profile'

const avatarPrefix = '?imageView2/1/w/80/h/80'
const carouselPrefix = '?imageView2/2/h/440'

export default {
  data() {
    return {
      carouselImages: [
        'https://camo.githubusercontent.com/6d035d4fd1eac38f5a9f81b7aa9fb38b257740bee5afb4d21c723ea1a80648b6/68747470733a2f2f636e2e62696e672e636f6d2f74683f69643d4f48522e4e6f7277617952657374417265615f454e2d5553333437343236383030385f5548442e6a7067267069643d687026773d33383426683d3231362672733d3126633d34',
        'https://camo.githubusercontent.com/b037b3e30da162ef40f391d2286c68b1bf49b02c20c5c746270d045b33584def/68747470733a2f2f636e2e62696e672e636f6d2f74683f69643d4f48522e4d616e61726f6c614974616c795f454e2d5553343832363534333339355f5548442e6a7067267069643d687026773d33383426683d3231362672733d3126633d34',
        'https://camo.githubusercontent.com/f59a5c1216a4bed0b045b0490f1ed795e2908f80ad09679c9758c7a4308b119f/68747470733a2f2f636e2e62696e672e636f6d2f74683f69643d4f48522e596950656e674c616e7465726e735f454e2d5553323838393830313139385f5548442e6a7067267069643d687026773d33383426683d3231362672733d3126633d34',
        'https://camo.githubusercontent.com/547d55ed3b0d5806eb52ca125b97ed67d88ba91d5378c274728169c78f3ea280/68747470733a2f2f636e2e62696e672e636f6d2f74683f69643d4f48522e506f6c61724265617246726f73745f454e2d5553393838383734313434305f5548442e6a7067267069643d687026773d33383426683d3231362672733d3126633d34'
      ],
      carouselPrefix,
      Announcements: [],
      avatarPrefix
    }
  },
  created() {
    this.fetchAnnouncements()
  },
  methods: {
    fetchAnnouncements() {
      getAnnouncements().then(response => {
        this.Announcements = response.data
        console.log(response.Announcements)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.user-activity {
  .user-block {

    .username,
    .description {
      display: block;
      margin-left: 50px;
      padding: 2px 0;
    }

    .username{
      font-size: 16px;
      color: #000;
    }

    :after {
      clear: both;
    }

    .img-circle {
      border-radius: 50%;
      width: 40px;
      height: 40px;
      float: left;
    }

    span {
      font-weight: 500;
      font-size: 12px;
    }
  }

  .post {
    font-size: 14px;
    border-bottom: 1px solid #d2d6de;
    margin-bottom: 15px;
    padding-bottom: 15px;
    color: #666;

    .image {
      width: 100%;
      height: 100%;

    }

    .user-images {
      padding-top: 20px;
    }
  }

  .list-inline {
    padding-left: 0;
    margin-left: -5px;
    list-style: none;

    li {
      display: inline-block;
      padding-right: 5px;
      padding-left: 5px;
      font-size: 13px;
    }

    .link-black {

      &:hover,
      &:focus {
        color: #999;
      }
    }
  }

}

.box-center {
  margin: 0 auto;
  display: table;
}

.text-muted {
  color: #777;
}
</style>
