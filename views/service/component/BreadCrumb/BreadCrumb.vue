<template>
    <div style="display: flex;flex-direction: row">
        <div v-for="(breadcrumbItem , index) in breadcrumbList" style="display: flex;flex-direction: row">
            <div v-if="breadcrumbItem.url">
                <a :href="contextPath+breadcrumbItem.url" style="">{{breadcrumbItem.itemName}}</a>
            </div>
            <div v-else>
                <span v-for="(subItem , subIndex) in breadcrumbItem">
                    <a :href="contextPath+subItem.url" style="">{{subItem.itemName}}</a>
                    <span v-if="subIndex<breadcrumbItem.length-1" style="color: #757575"> </span>
                </span>
            </div>
            <div v-if="index<breadcrumbList.length-1" style="color: #757575">\</div>
        </div>
    </div>
</template>
<script>
    import Urls from "../../../utils/Urls";

    export default {
        name: 'breadcrumb',
        props: {
            breadcrumbList: {
                default: []
            },
            contextPath: {
                default: Urls.CONTEXT_PATH
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
        color: #607D8B;
    }

    a:hover {
        color: #455A64;
    }
</style>