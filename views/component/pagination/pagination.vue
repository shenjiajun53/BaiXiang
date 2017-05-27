<template>
    <div style="display: flex;flex-direction: row" class="pagination">
        <div v-if="showFirstBtn"><a :href="originHref+'&page='+1">首页</a></div>
        <div v-if="showPreBtn"><a :href="originHref+'&page='+(currentPage-1)">上一页</a></div>
        <div v-for="pageItem in pageList" style="margin-left: 1em">
            <a :href="originHref+'&page='+pageItem.itemValue">{{pageItem.itemValue}}</a>
        </div>
        <div v-if="showNextBtn"><a :href="originHref+'&page='+(currentPage+1)">下一页</a></div>
        <div v-if="showLastBtn"><a :href="originHref+'&page='+maxPage">末页</a></div>
    </div>
</template>

<script>
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
    let currentPage = Number(getQueryString("page"));
    if (currentPage === 0) {
        currentPage = 1;
    }
    let currentTag = getQueryString("tag");
    console.log("href=" + window.location.href);
    console.log("tag=" + currentTag);

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
