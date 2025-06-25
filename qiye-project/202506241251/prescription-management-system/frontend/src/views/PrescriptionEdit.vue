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
import { mapGetters } from 'vuex';

export default {
  components: {
    ImageUploader,
    ImageViewer,
  },
  data() {
    return {
      prescription: {
        name: '',
        indications: '',
        usage: '',
        treatmentCycle: '',
      },
      selectedDrugs: [],
      prescriptionImages: [],
      drugs: [], // This should be populated with drug data from the API
    };
  },
  computed: {
    ...mapGetters(['getDrugs']),
  },
  methods: {
    submitForm() {
      // Logic to submit the prescription data to the backend
    },
    handleImageUpload(images) {
      this.prescriptionImages = images;
    },
    fetchDrugs() {
      // Logic to fetch drugs from the API and set this.drugs
    },
  },
  mounted() {
    this.fetchDrugs();
  },
};
</script>

<style scoped>
.prescription-edit {
  max-width: 600px;
  margin: auto;
}
</style>