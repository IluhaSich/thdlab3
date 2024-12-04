<script setup>
    import UrlComponent from '@/components/UrlComponent.vue'
    import TableComponent from '@/components/TableComponent.vue'
</script>

<template>
  <main class="main">
    <UrlComponent/>
    <TableComponent/>
  </main>
</template>

<style>
.main {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
}
</style>