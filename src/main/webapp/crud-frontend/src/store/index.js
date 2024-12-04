import { createStore } from 'vuex';
import axios from 'axios';

const store = createStore({
    state: {
        apiUrl: new URL('http://localhost:8080/'),
        book: null,
        books: [],
        isEmpty: false,
        totalPages: 0,
        currentPage: 0,
        countOnPage: 10,
    },
    getters: {
        getApiUrl(state) {
            return state.apiUrl;
        },
        getBook(state) {
            return state.book;
        },
        getBooks(state) {
            return state.books;
        },
        getIsEmpty(state) {
            return state.isEmpty;
        },
        getTotalPages(state) {
            return state.totalPages;
        },
        getCurrentPage(state) {
            return state.currentPage;
        },
        getCountOnPage(state) {
            return state.countOnPage;
        }
    },
    mutations: {
        saveApiUrl(state, payload) {
            state.apiUrl = payload;
        },
        setBook(state, payload) {
            state.book = payload;
        },
        setBooks(state, payload) {
            state.books = payload;
        },
        setIsEmpty(state, payload) {
            state.isEmpty = payload;
        },
        setTotalPages(state, payload) {
            state.totalPages = payload;
        },
        setCurrentPage(state, payload) {
            state.currentPage = payload;
        },
        setCountOnPage(state, payload) {
            state.countOnPage = payload;
        }
    },
    actions: {
        loadBooks(context) {
            axios.get(context.getters.getApiUrl, {
                params: {
                    offset: context.getters.getCurrentPage,
                    limit: context.getters.getCountOnPage,
                }
            })
            .then(r =>  {
                if (r.data && r.data.content) {
                    context.commit('setBooks', r.data.content);
                    context.commit('setTotalPages', r.data.totalPages);
                    context.commit('setCurrentPage', r.data.number);
                    context.commit('setIsEmpty', false);
                } else {
                    context.commit('setIsEmpty', true);
                }
            })
            .catch(error => {
                context.commit('setIsEmpty', true);
                console.log(error);
            });
        },
        loadBookById(context, id) {
            axios.get(context.getters.getApiUrl + '/' + id)
            .then(r => {
                if (r.data) {
                    context.commit('setBook', r.data);
                } else {
                    alert('Запись не найдена');
                    console.log(error);
                }
            })
            .catch(error => {
                alert('Запись не найдена');
                console.log(error);
            })
        },
        saveBook(context, book) {
            axios.post(context.getters.getApiUrl, 
                JSON.stringify(book), {
                headers: {
                    'Content-Type': 'application/json',
                },
            })
            .then(r => {
                alert('Запись добавлена');
            })
            .catch(error => {
                alert('Возникла ошибка');
                console.log(error);
            });
        },
        updateBook(context, book) {
            axios.put(context.getters.getApiUrl + '/' + book['id'], 
                JSON.stringify(book), {
                headers: {
                    'Content-Type': 'application/json',
                },
            })
            .then(r => {
                alert('Запись обновлена');
            })
            .catch(error => {
                alert('Возникла ошибка');
                console.log(error);
            });
        },
        deleteBook(context, id) {
            axios.delete(context.getters.getApiUrl + '/' + id)
            .then(
                context.commit('setBooks', context.getters.getBooks.filter((item) => {
                    return Object.values(item)[0] !== id;
                }))
            )
            .catch(error => {
                console.log(error);
            });
        }
    },
});

export default store;