<template>
    <section>
        <input type="text" class="url-input" v-model="url">
    </section>
</template>

<script>
    export default {
        name: 'UrlComponent',
        computed: {
            url: {
                get() {
                    return this.$store.getters.getApiUrl;
                },
                set (value) {
                    this.$store.commit('saveApiUrl', value);
                }
            }
        }
    }
</script>

<style>
    .url-input {
        margin-top: 1rem;
        width: 100%;
    }
</style>