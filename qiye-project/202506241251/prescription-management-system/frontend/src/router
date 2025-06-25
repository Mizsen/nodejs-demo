import { createRouter, createWebHistory } from 'vue-router';
import PrescriptionEdit from '../views/PrescriptionEdit.vue';
import PrescriptionDetail from '../views/PrescriptionDetail.vue';
import DrugEdit from '../views/DrugEdit.vue';
import DrugDetail from '../views/DrugDetail.vue';

const routes = [
  {
    path: '/prescription/edit',
    name: 'PrescriptionEdit',
    component: PrescriptionEdit,
  },
  {
    path: '/prescription/:id',
    name: 'PrescriptionDetail',
    component: PrescriptionDetail,
  },
  {
    path: '/drug/edit',
    name: 'DrugEdit',
    component: DrugEdit,
  },
  {
    path: '/drug/:id',
    name: 'DrugDetail',
    component: DrugDetail,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;