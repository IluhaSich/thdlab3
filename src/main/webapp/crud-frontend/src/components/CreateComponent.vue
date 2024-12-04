<template>
    <div v-if="Object.keys(headers).length !== 0">
        <section class="content-section">
            <div v-for="header in headers" class="input-line">
                <label class="input-line__label">
                    {{ header }}
                    <button v-if="header == 'id'" :disabled="!('id' in values)" class="input-line__button" v-on:click="getBookById()">-></button>
                </label>
                <input type="text" v-model="values[header]">
            </div>
            <div class="input-buttons">
                <button v-on:click="createBook()">Создать</button>
                <button v-if="values['id']" v-on:click="updateBook()">Обновить</button>
            </div>
        </section>
    </div>
    <div v-else>
        <section class="content-section">
            <p>Не удалось прочитать поля</p>
        </section>
    </div>
</template>

<script>
    import { mapState } from 'vuex';
    
    export default {
        data() {
            return {
                values: {}
            }
        },
        computed: {
            ...mapState({
                book: 'book'
            }),
            headers() {
                if (this.$store.getters.getBooks.length) {
                    return Object.keys(this.$store.getters.getBooks[0]);
                } else {
                    return [];
                }
            },
        },
        watch: {
            book: function (newBook, oldBook) {
                this.values = newBook;
            }
        },
        methods: {
            getBookById() {
                this.$store.dispatch('loadBookById', this.values['id']);
            },
            createBook() {
                this.$store.dispatch('saveBook', this.values);
            },
            updateBook() {
                this.$store.dispatch('updateBook', this.values);
            }
        }
    };
</script>

<style>
    .input-line__button {
        max-width: 40px;
    }
    .input-buttons {
        text-align: center;
    }
</style>