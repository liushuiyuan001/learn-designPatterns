import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
const name = sessionStorage.getItem('loginName') || ''
const token = sessionStorage.getItem('loginToken') || ''

export const useUserStore = defineStore('counter', () => {
  const loginName = ref(name)
  const loginToken = ref(token)
  const doubleCount = computed(() => loginName.value + 2)
  function updateLoginName(newName: string) {
    loginName.value = newName
    sessionStorage.setItem('loginName', newName)
  }

  function updateLoginToken(newToken: string) {
    loginToken.value = newToken
    sessionStorage.setItem('loginToken', newToken)
  }

  return { loginName, doubleCount, updateLoginName, loginToken, updateLoginToken }
})
