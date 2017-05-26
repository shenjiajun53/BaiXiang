<template>
    <div style="display: flex;flex-direction: row">
        <div v-for="pageItem in pageList" style="margin-left: 1em">
            <a :href="originHref+'&page='+pageItem.itemValue">{{pageItem.itemValue}}</a>
        </div>
    </div>
</template>

<script>

</script>