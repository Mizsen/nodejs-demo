<template>
  <div class="image-uploader">
    <input type="file" multiple @change="handleFileUpload" accept="image/*" />
    <div class="image-preview" v-if="images.length">
      <h3>Image Preview</h3>
      <div class="preview-container">
        <img v-for="(image, index) in images" :key="index" :src="image" class="preview-image" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      images: []
    };
  },
  methods: {
    handleFileUpload(event) {
      const files = event.target.files;
      this.images = [];
      for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const reader = new FileReader();
        reader.onload = (e) => {
          this.images.push(e.target.result);
        };
        reader.readAsDataURL(file);
      }
    }
  }
};
</script>

<style scoped>
.image-uploader {
  margin: 20px;
}

.image-preview {
  margin-top: 10px;
}

.preview-container {
  display: flex;
  flex-wrap: wrap;
}

.preview-image {
  width: 100px;
  height: 100px;
  object-fit: cover;
  margin-right: 10px;
  margin-bottom: 10px;
}
</style>