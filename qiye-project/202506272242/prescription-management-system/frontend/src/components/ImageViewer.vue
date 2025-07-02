<template>
  <div class="image-viewer">
    <img :src="currentImage" alt="Image Viewer" class="image" v-if="currentImage" />
    <div class="controls">
      <button @click="prevImage" :disabled="currentIndex === 0">上一张</button>
      <button @click="nextImage" :disabled="currentIndex === images.length - 1">下一张</button>
      <button @click="zoomIn">Zoom In</button>
      <button @click="zoomOut">Zoom Out</button>
      <button @click="rotateLeft">Rotate Left</button>
      <button @click="rotateRight">Rotate Right</button>
    </div>
    <div v-if="images.length > 1" class="image-indicator">
      {{ currentIndex + 1 }} / {{ images.length }}
    </div>
  </div>
</template>

<script>
import { computed } from 'vue';

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
      if (this.images && this.images.length > 0) {
        return this.images[this.currentIndex]?.imagePath || this.images[this.currentIndex]?.src || null;
      }
      return null;
    }
  },
  methods: {
    prevImage() {
      if (this.currentIndex > 0) {
        this.currentIndex--;
        this.resetTransform();
      }
    },
    nextImage() {
      if (this.currentIndex < this.images.length - 1) {
        this.currentIndex++;
        this.resetTransform();
      }
    },
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
      if (img) {
        img.style.transform = `scale(${this.scale}) rotate(${this.rotation}deg)`;
      }
    },
    resetTransform() {
      this.scale = 1;
      this.rotation = 0;
      this.updateImageStyle();
    }
  },
  watch: {
    currentIndex() {
      this.resetTransform();
    },
    images() {
      if (this.currentIndex >= this.images.length) {
        this.currentIndex = 0;
      }
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

.image-indicator {
  margin-top: 5px;
  color: #888;
}
</style>