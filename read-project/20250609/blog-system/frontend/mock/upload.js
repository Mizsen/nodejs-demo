export default [
  {
    url: '/api/upload',
    method: 'post',
    response: () => ({
      code: 0,
      data: {
        url: '/uploads/mock-image.png',
        type: 'image'
      }
    })
  }
] 