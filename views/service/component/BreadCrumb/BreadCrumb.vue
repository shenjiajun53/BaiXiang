<template>
    <div style="display: flex;flex-direction: row">
        <div v-for="(breadcrumbItem , index) in breadcrumbList" style="display: flex;flex-direction: row">
            <div v-if="breadcrumbItem.url">
                <a :href="breadcrumbItem.url" style="">{{breadcrumbItem.itemName}}</a>
            </div>
            <div v-else>
                <div v-for="(subItem , subIndex) in breadcrumbItem">
                    <a :href="subItem.url" style="">{{subItem.itemName}}</a>
                    <div v-if="subIndex<subItem.length-1" style="color: #757575">,</div>
                </div>
            </div>
            <div v-if="index<breadcrumbList.length-1" style="color: #757575">\</div>
        </div>
    </div>
</template>
<script>
    export default {
        name: 'breadcrumb',
        props: {
            breadcrumbList: {
                default: []
            },
        },
    }
</script>
<style scoped>
    a {
        text-decoration: none;
    }

    a:link {
        color: #03A9F4;
    }

    a:visited {
        color: #E91E63;
    }

    a:hover {
        color: #C2185B;
    }
</style>