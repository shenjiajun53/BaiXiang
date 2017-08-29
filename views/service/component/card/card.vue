<template>
    <div class="card">
        <slot></slot>
    </div>
</template>

<script>
    export default{
        name: 'card',
        props:{

        }
    }
</script>
<style scoped>
    .card {
        border: 1px solid #d1dbe5;
        border-radius: 4px;
        background-color: #FFFFFF;
        overflow: hidden;
        transition: box-shadow 0.5s;
        box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .12), 0 0 6px 0 rgba(0, 0, 0, .04);
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .card img {
        width: 100%;
        object-fit: cover;
        border-radius: 4px 4px 0 0;
    }

    .card:hover {
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, .24), 0 0 12px 0 rgba(0, 0, 0, .08)
    }

    .card a{
        text-decoration: none;
    }
</style>