<template>
  <div class="prescription-edit">
    <h2>Edit Prescription</h2>
    <form @submit.prevent="submitForm">
      <div>
        <label for="prescriptionName">Prescription Name:</label>
        <input type="text" v-model="prescription.name" id="prescriptionName" required />
      </div>
      <div>
        <label for="indications">Indications:</label>
        <textarea v-model="prescription.indications" id="indications" required></textarea>
      </div>
      <div>
        <label for="usage">Usage:</label>
        <textarea v-model="prescription.usage" id="usage" required></textarea>
      </div>
      <div>
        <label for="treatmentCycle">Treatment Cycle:</label>
        <input type="text" v-model="prescription.treatmentCycle" id="treatmentCycle" required />
      </div>
      <div>
        <label>Associated Drugs:</label>
        <select v-model="selectedDrugs" multiple>
          <option v-for="drug in drugs" :key="drug.id" :value="drug.id">{{ drug.name }}</option>
        </select>
      </div>
      <ImageUploader @upload="handleImageUpload" />
      <button type="submit">Save Prescription</button>
    </form>
    <ImageViewer :images="prescriptionImages" />
  </div>
</template>

<script>
import ImageUploader from '@/components/ImageUploader.vue';
import ImageViewer from '@/components/ImageViewer.vue';
import { useRoute } from 'vue-router';
import { prescriptionApi, drugApi } from '@/api/index.js';

export default {
  components: {
    ImageUploader,
    ImageViewer,
  },
  setup() {
    const route = useRoute();
    const prescription = ref({
      name: '',
      indications: '',
      usage: '',
      treatmentCycle: '',
    });
    const selectedDrugs = ref([]);
    const prescriptionImages = ref([]);
    const drugs = ref([]);

    // 拉取药品列表
    const fetchDrugs = async () => {
      const res = await drugApi.getAll();
      drugs.value = res.data;
    };

    // 拉取药方详情
    const fetchPrescriptionDetail = async () => {
      const id = route.params.id;
      if (!id) return;
      const res = await prescriptionApi.getPrescription(id);
      const data = res.data;
      prescription.value = {
        name: data.prescription_name,
        indications: data.indications,
        usage: data.usage,
        treatmentCycle: data.treatment_cycle,
      };
      selectedDrugs.value = data.drugs ? data.drugs.map(d => d.id) : [];
      prescriptionImages.value = data.images || [];
    };

    // 提交表单
    const submitForm = async () => {
      const id = route.params.id;
      await prescriptionApi.updatePrescription(id, {
        prescription_name: prescription.value.name,
        indications: prescription.value.indications,
        usage: prescription.value.usage,
        treatment_cycle: prescription.value.treatmentCycle,
        drug_ids: selectedDrugs.value,
        images: prescriptionImages.value,
      });
      // 可加提示和跳转
    };

    const handleImageUpload = (images) => {
      prescriptionImages.value = images;
    };

    onMounted(() => {
      fetchDrugs();
      fetchPrescriptionDetail();
    });

    return {
      prescription,
      selectedDrugs,
      prescriptionImages,
      drugs,
      submitForm,
      handleImageUpload,
    };
  },
};
</script>

<style scoped>
.prescription-edit {
  max-width: 600px;
  margin: auto;
}
</style>