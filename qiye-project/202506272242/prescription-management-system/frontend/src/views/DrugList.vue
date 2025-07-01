<template>
  <div class="drug-list">
    <h2>药品列表</h2>
    <div class="search-bar">
      <input v-model="searchName" placeholder="药品名称关键词" />
      <input v-model="searchIndications" placeholder="适应症关键词" />
      <button @click="fetchList">搜索</button>
      <button @click="resetSearch">重置</button>
    </div>
    <el-table :data="drugs" style="width: 100%">
      <el-table-column prop="id" label="药品ID" />
      <el-table-column prop="drugName" label="药品名称" />
      <el-table-column prop="specification" label="规格" />
      <el-table-column prop="manufacturer" label="生产厂家" />
      <el-table-column prop="indications" label="适应症" />
      <el-table-column label="图片">
        <template #default="scope">
          <img v-for="img in scope.row.images" :key="img.id" :src="img.image_path" style="max-width:60px;margin-right:4px;" />
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="editDrug(scope.row.id)">编辑</el-button>
          <el-button size="small" type="danger" @click="deleteDrug(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next, total"
      :total="total"
      :page-size="pageSize"
      :current-page="page"
      @current-change="handlePageChange"
    />
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { drugApi } from '@/api/index.js';

export default {
  setup() {
    const router = useRouter();
    const drugs = ref([]);
    const total = ref(0);
    const page = ref(1);
    const pageSize = ref(10);
    const searchName = ref('');
    const searchIndications = ref('');

    const fetchList = async () => {
      const res = await drugApi.listDrugs({
        page: page.value,
        size: pageSize.value,
        name: searchName.value,
        indications: searchIndications.value,
      });
      drugs.value = res.data.list;
      total.value = res.data.total;
    };

    const handlePageChange = (val) => {
      page.value = val;
      fetchList();
    };

    const resetSearch = () => {
      searchName.value = '';
      searchIndications.value = '';
      fetchList();
    };

    const editDrug = (id) => {
      router.push(`/admin/drug/edit/${id}`);
    };

    const deleteDrug = async (id) => {
      if (confirm('确定要删除该药品吗？')) {
        await drugApi.deleteDrug(id);
        fetchList();
      }
    };

    onMounted(() => {
      fetchList();
    });

    return {
      drugs,
      total,
      page,
      pageSize,
      searchName,
      searchIndications,
      fetchList,
      handlePageChange,
      resetSearch,
      editDrug,
      deleteDrug,
    };
  },
};
</script>

<style scoped>
.drug-list {
  max-width: 1000px;
  margin: auto;
}
.search-bar {
  margin-bottom: 16px;
}
</style>
