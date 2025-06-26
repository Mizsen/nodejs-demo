<template>
  <div class="drug-edit">
    <h2>Edit Drug</h2>
    <el-form :model="drug" ref="drugForm" label-width="120px">
      <el-form-item label="Drug Name" prop="drug_name">
        <el-input v-model="drug.drug_name" placeholder="Enter drug name"></el-input>
      </el-form-item>
      <el-form-item label="Specification" prop="specification">
        <el-input v-model="drug.specification" placeholder="Enter specification"></el-input>
      </el-form-item>
      <el-form-item label="Manufacturer" prop="manufacturer">
        <el-input v-model="drug.manufacturer" placeholder="Enter manufacturer"></el-input>
      </el-form-item>
      <el-form-item label="Indications" prop="indications">
        <el-input type="textarea" v-model="drug.indications" placeholder="Enter indications"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateDrug">Update Drug</el-button>
        <el-button @click="cancel">Cancel</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { updateDrugApi } from '@/api/drug';

export default {
  setup() {
    const route = useRoute();
    const router = useRouter();
    const drug = ref({
      drug_name: '',
      specification: '',
      manufacturer: '',
      indications: ''
    });

    const loadDrug = async () => {
      const drugId = route.params.id;
      // Fetch drug details from API and set to drug
      // Example: drug.value = await fetchDrugDetails(drugId);
    };

    const updateDrug = async () => {
      await updateDrugApi(drug.value);
      router.push('/drugs'); // Redirect to drugs list after update
    };

    const cancel = () => {
      router.push('/drugs'); // Redirect to drugs list on cancel
    };

    loadDrug();

    return {
      drug,
      updateDrug,
      cancel
    };
  }
};
</script>

<style scoped>
.drug-edit {
  padding: 20px;
}
</style>