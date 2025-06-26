<template>
  <div class="image-viewer">
    <img :src="currentImage" alt="Image Viewer" class="image" />
    <div class="controls">
      <button @click="zoomIn">Zoom In</button>
      <button @click="zoomOut">Zoom Out</button>
      <button @click="rotateLeft">Rotate Left</button>
      <button @click="rotateRight">Rotate Right</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    images: {
      type: Array,
      required: true
    },
    initialIndex: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      currentIndex: this.initialIndex,
      scale: 1,
      rotation: 0
    };
  },
  computed: {
    currentImage() {
      return this.images[this.currentIndex];
    }
  },
  methods: {
    zoomIn() {
      this.scale += 0.1;
      this.updateImageStyle();
    },
    zoomOut() {
      if (this.scale > 0.1) {
        this.scale -= 0.1;
        this.updateImageStyle();
      }
    },
    rotateLeft() {
      this.rotation -= 90;
      this.updateImageStyle();
    },
    rotateRight() {
      this.rotation += 90;
      this.updateImageStyle();
    },
    updateImageStyle() {
      const img = this.$el.querySelector('.image');
      img.style.transform = `scale(${this.scale}) rotate(${this.rotation}deg)`;
    }
  }
};
</script>

<style scoped>
.image-viewer {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.image {
  max-width: 100%;
  transition: transform 0.2s;
}

.controls {
  margin-top: 10px;
}
</style>