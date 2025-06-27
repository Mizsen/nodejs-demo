<template>
  <div class="drug-detail">
    <h1>{{ drug.drug_name }}</h1>
    <p><strong>规格:</strong> {{ drug.specification }}</p>
    <p><strong>生产厂家:</strong> {{ drug.manufacturer }}</p>
    <p><strong>适应症:</strong> {{ drug.indications }}</p>
    <div class="image-gallery">
      <ImageViewer v-for="image in drugImages" :key="image.id" :src="image.image_path" />
    </div>
    <router-link to="/drugs" class="back-button">返回药品列表</router-link>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { getDrugDetail } from '@/api/drug'; // Assume this API function is defined
import ImageViewer from '@/components/ImageViewer.vue';

export default {
  components: {
    ImageViewer,
  },
  setup() {
    const route = useRoute();
    const drug = ref({});
    const drugImages = ref([]);

    const fetchDrugDetail = async () => {
      const drugId = route.params.id;
      const response = await getDrugDetail(drugId);
      drug.value = response.data;
      drugImages.value = response.data.images; // Assuming images are part of the drug detail response
    };

    onMounted(fetchDrugDetail);

    return {
      drug,
      drugImages,
    };
  },
};
</script>

<style scoped>
.drug-detail {
  padding: 20px;
}

.image-gallery {
  display: flex;
  flex-wrap: wrap;
}

.back-button {
  margin-top: 20px;
  display: inline-block;
  text-decoration: none;
  color: #007bff;
}
</style>