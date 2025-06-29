<template>
  <div class="prescription-detail">
    <h1>{{ prescription.prescription_name }}</h1>
    <p><strong>适应症:</strong> {{ prescription.indications }}</p>
    <p><strong>用法与用量:</strong> {{ prescription.usage }}</p>
    <p><strong>治疗周期:</strong> {{ prescription.treatment_cycle }}</p>
    
    <h2>关联药品</h2>
    <ul>
      <li v-for="drug in prescription.drugs" :key="drug.id">
        {{ drug.drug_name }} ({{ drug.specification }}) - {{ drug.manufacturer }}
      </li>
    </ul>

    <h2>药方图片</h2>
    <div class="image-gallery">
      <ImageViewer v-for="image in prescription.images" :key="image.id" :src="image.image_path" />
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { prescriptionApi } from '@/api/index.js';
import ImageViewer from '@/components/ImageViewer.vue';

export default {
  components: {
    ImageViewer,
  },
  setup() {
    const route = useRoute();
    const prescription = ref({});

    const fetchPrescriptionDetail = async () => {
      const response = await prescriptionApi.getPrescription(route.params.id);
      prescription.value = response.data;
    };

    onMounted(() => {
      fetchPrescriptionDetail();
    });

    return {
      prescription,
    };
  },
};
</script>

<style scoped>
.prescription-detail {
  padding: 20px;
}

.image-gallery {
  display: flex;
  flex-wrap: wrap;
}

.image-gallery img {
  max-width: 100px;
  margin: 10px;
}
</style>