<template>
    <div>
        <span v-for="(breadcrumbItem , index) in breadcrumbList">
            <a :href="breadcrumbItem.url">{{breadcrumbItem.tagName}}</a>
            <span v-if="index<breadcrumbList.length-1">/</span>
        </span>
    </div>
</template>
<script>
    export default{
        name: 'breadcrumb',
        props: {
            breadcrumbList: {
                default: []
            },
        },
    }
</script>