<template>
    <div>
        <f-header></f-header>
        <div class="center">
            <div class="content markdown-body" v-html="blog.blogContent">
            </div>


        </div>
    </div>
</template>


<script>
    import axios from "axios";
    import 'github-markdown-css/github-markdown.css' // 然后添加样式markdown-body
    export default {
        name: "BlogDetail",
        data() {
            return {
                blog: {
                    blogTitle: "",
                    blogDesc: "",
                    blogContent: ""
                },
            }
        },
        methods: {
            getBlog() {
                const blogId = this.$route.query.id
                const _this = this
                axios.post('/getBlogById/' + blogId).then((res) => {
                    _this.blog = res.data.data
                    var MarkdownIt = require('markdown-it'),
                        md = new MarkdownIt();
                    var result = md.render(_this.blog.blogContent);
                    console.log("========****" + result)
                    _this.blog.blogContent = result
                });
            }
        },
        created() {
            this.getBlog()
        }
    }
</script>

<style scoped>
    .center {
        width: 75%;
        margin: 20px auto;
        opacity: 0.8;

    }

    .content {
        height: 8000px;
        overflow: auto;
    }

</style>