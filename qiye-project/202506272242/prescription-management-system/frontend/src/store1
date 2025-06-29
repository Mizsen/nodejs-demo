const state = {
  currentPrescription: null,
  prescriptionImages: [],
  drugImages: {}
};

const mutations = {
  SET_CURRENT_PRESCRIPTION(state, data) {
    state.currentPrescription = data;
  },
  ADD_PRESCRIPTION_IMAGE(state, image) {
    state.prescriptionImages.push(image);
  },
  SET_DRUG_IMAGES(state, images) {
    state.drugImages = images;
  }
};

const actions = {
  fetchPrescription({ commit }, id) {
    // Fetch prescription data from API and commit to state
  },
  savePrescription({ commit }, prescription) {
    // Save prescription data to API
  },
  uploadPrescriptionImages({ commit }, images) {
    // Upload prescription images to API
  },
  fetchDrugImages({ commit }, drugId) {
    // Fetch drug images from API and commit to state
  }
};

const getters = {
  currentPrescription: (state) => state.currentPrescription,
  prescriptionImages: (state) => state.prescriptionImages,
  drugImages: (state) => state.drugImages
};

export default {
  state,
  mutations,
  actions,
  getters
};