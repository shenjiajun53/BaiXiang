<template>
    <div style="display: flex;flex-direction: row" class="pagination">
        <div v-if="showFirstBtn"><a :href="originHref+'&page='+1" class="character-item">首页</a></div>
        <div v-if="showPreBtn"><a :href="originHref+'&page='+(currentPage-1)" class="character-item">上一页</a></div>
        <div v-for="pageItem in pageList">
            <a :href="originHref+'&page='+pageItem.itemValue" class="number-item">{{pageItem.itemValue}}</a>
        </div>
        <div v-if="showNextBtn"><a :href="originHref+'&page='+(currentPage+1)" class="character-item">下一页</a></div>
        <div v-if="showLastBtn"><a :href="originHref+'&page='+maxPage" class="character-item">末页</a></div>
    </div>
</template>

<script>
    import UrlUtil from "../../utils/UrlUtil";
    let currentPage = Number(new UrlUtil().getQueryString("page"));
    if (currentPage === 0) {
        currentPage = 1;
    }
    let currentTag = new UrlUtil().getQueryString("tag");
    //    console.log("href=" + window.location.href);
    //    console.log("tag=" + currentTag);

    export default{
        name: 'pagination',
        props: {
            maxPage: {
                type: Number,
                default: 0
            },
        },
        data: function () {
            return {
                originHref: window.location.pathname + "?tag=" + currentTag,
                pageList: [],
                currentPage: currentPage,
                showPreBtn: true,
                showNextBtn: true,
                showFirstBtn: true,
                showLastBtn: true
            }
        },
        beforeMount: function () {
            let maxPage = this.maxPage;
            console.log("page=" + currentPage + " maxPage=" + maxPage);
            if (currentPage === 1) {
                this.showPreBtn = false;
                this.showFirstBtn = false;
            }
            if (currentPage === maxPage) {
                this.showNextBtn = false;
                this.showLastBtn = false;
            }
            if (maxPage > 5) {
                if (currentPage === 1) {
                    this.pageList = [
                        {itemValue: 1, checked: true},
                        {itemValue: 2, checked: false},
                        {itemValue: 3, checked: false},
                        {itemValue: 4, checked: false},
                        {itemValue: 5, checked: false}]
                } else if (currentPage === 2) {
                    this.pageList = [
                        {itemValue: 1, checked: false},
                        {itemValue: 2, checked: true},
                        {itemValue: 3, checked: false},
                        {itemValue: 4, checked: false},
                        {itemValue: 5, checked: false}]
                } else if (maxPage === currentPage) {
                    this.pageList = [
                        {itemValue: currentPage - 4, checked: false},
                        {itemValue: currentPage - 3, checked: false},
                        {itemValue: currentPage - 2, checked: false},
                        {itemValue: currentPage - 1, checked: false},
                        {itemValue: currentPage, checked: true}]
                } else if ((maxPage - currentPage) === 1) {
                    this.pageList = [
                        {itemValue: currentPage - 3, checked: false},
                        {itemValue: currentPage - 2, checked: false},
                        {itemValue: currentPage - 1, checked: false},
                        {itemValue: currentPage, checked: true},
                        {itemValue: currentPage + 1, checked: false}]
                } else {
                    this.pageList = [
                        {itemValue: currentPage - 2, checked: false},
                        {itemValue: currentPage - 1, checked: false},
                        {itemValue: currentPage, checked: true},
                        {itemValue: currentPage + 1, checked: false},
                        {itemValue: currentPage + 2, checked: false}]
                }
            } else {
                for (let i = 1; i < maxPage + 1; i++) {
                    this.pageList.push({itemValue: i, checked: i === currentPage});
                }
            }
        },
        computed: {}
    }

</script>
<style scoped>
    .number-item {
        height: 1em;
        width: 1em;
        padding: 0.5em;
        border-radius: 2em;
    }

    .character-item {
        height: 1em;
        padding: 0.5em;
        border-radius: 1em;
    }

    a {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-left: 0.5em;
        color: white;
        text-decoration: none;
        background-color: #E91E63;
        transition: background-color 0.3s;
    }

    a:hover {
        background-color: #C2185B;
    }

    a:active {
        background-color: #F8BBD0;
    }

    a:link {

    }

    a:visited {

    }
</style>