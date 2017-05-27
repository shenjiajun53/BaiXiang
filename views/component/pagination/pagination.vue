<template>
    <div style="display: flex;flex-direction: row" class="pagination">
       afewefeawfawefre
    </div>
</template>

<script>
//    function getQueryString(name) {
//        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
//        var r = window.location.search.substr(1).match(reg);
//        if (r != null) return unescape(r[2]);
//        return null;
//    }
//    let currentPage = Number(getQueryString("page"));
//    let currentTag = getQueryString("tag");
//    let maxPage = this.props.maxPage;
//    console.log("href=" + window.location.href);
//    console.log("page=" + currentPage + " maxPage=" + maxPage);
//    console.log("tag=" + currentTag);
//    let pageList = [];
//    if (maxPage > 5) {
//        if (currentPage === 1) {
//            pageList = [
//                {itemValue: 1, checked: true},
//                {itemValue: 2, checked: false},
//                {itemValue: 3, checked: false},
//                {itemValue: 4, checked: false},
//                {itemValue: 5, checked: false}]
//        } else if (currentPage === 2) {
//            pageList = [
//                {itemValue: 1, checked: false},
//                {itemValue: 2, checked: true},
//                {itemValue: 3, checked: false},
//                {itemValue: 4, checked: false},
//                {itemValue: 5, checked: false}]
//        } else if (maxPage === currentPage) {
//            pageList = [
//                {itemValue: currentPage - 4, checked: false},
//                {itemValue: currentPage - 3, checked: false},
//                {itemValue: currentPage - 2, checked: false},
//                {itemValue: currentPage - 1, checked: false},
//                {itemValue: currentPage, checked: true}]
//        } else if ((maxPage - currentPage) === 1) {
//            pageList = [
//                {itemValue: currentPage - 3, checked: false},
//                {itemValue: currentPage - 2, checked: false},
//                {itemValue: currentPage - 1, checked: false},
//                {itemValue: currentPage, checked: true},
//                {itemValue: currentPage + 1, checked: false}]
//        } else {
//            pageList = [
//                {itemValue: currentPage - 2, checked: false},
//                {itemValue: currentPage - 1, checked: false},
//                {itemValue: currentPage, checked: true},
//                {itemValue: currentPage + 1, checked: false},
//                {itemValue: currentPage + 2, checked: false}]
//        }
//    } else {
//        for (let i = 1; i < maxPage + 1; i++) {
//            pageList.push({itemValue: i, checked: i === currentPage});
//        }
//    }

    export default{
        name: 'pagination',
        props: {
            maxPage: {
                type:Number,
                default:0
            },
            pageList: []
        }
    }

</script>
