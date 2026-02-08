<template xmlns="http://www.w3.org/1999/html">
    <h3 class="dividing">Comments | Tổng {{ commentStats.totalComments }} bình luận</h3>
    <CommentForm :reply-comment="{
       threadRoot: null,
       parentCommentId:null,
    }"></CommentForm>
    <div class="comments-list">
      <div v-for="cm in comments"
           :key="cm.id"
      >
        <div class="thread-root">
          <div class="comment-item">
            <Avatar
                :shape="'circle'"
                :image="'/src/assets/img/comment-avatar/'+cm.avatar"
                :size="'large'"
            />
            <div class="comment"  :ref="el => setItemRef(el,cm.id)">
              <div class="comment-header">
                <span class="nickname">{{cm.nickname}}</span>
                <div class="metadata">
                  {{ ` ${formatDate(cm.createTime,'YYYY-MM-DD HH:mm')}`}}
                </div>
                <button type="button" @click="setReplyComment(cm.threadRoot,cm.id)">Trả lời</button>
              </div>
              <div>{{cm.content}}</div>
            </div>
          </div>
          <div class="">
            <div  v-if="cm.replyComment"
                  v-for="replyCm in cm.replyComment"
                  :key="replyCm.id"
            >
              <div class="comment-item comment-reply">
                <Avatar
                    class="avt-l"
                    :shape="'circle'"
                    :image="'/src/assets/img/comment-avatar/'+replyCm.avatar"
                    :size="'normal'"
                />
                <div class="comment" :ref="el => setItemRef(el,replyCm.id)">
                  <div class="comment-header">
                    <span class="nickname">{{replyCm.nickname}}</span>
                    <div class="metadata">
                      {{ ` ${formatDate(replyCm.createTime,'YYYY-MM-DD HH:mm')}`}}
                    </div>
                    <button type="button" @click="setReplyComment(cm.threadRoot,replyCm.id)">Trả lời</button>
                  </div>
                  <div><span class="reply">{{`${replyCm.reply}`}}</span>{{ ` ${replyCm.content} `}}</div>
                </div>
              </div>
            </div>
          </div>
          <div v-if="replyComment.threadRoot === cm.threadRoot" class="comment-item comment-reply">
            <CommentForm :replyComment="replyComment"/>
          </div>
          <div class="thread-line"/>
        </div>
      </div>
    </div>

</template>

<script setup>
import {formatDate} from "@/util/dateTimeFormatUtils.js";
import CommentForm from "@/components/comments/CommentForm.vue";
import {ref, watch} from "vue";

const props = defineProps({
  comments: {
    type: Array,
    required: true,
  },
  commentStats: {
    type: Object,
    required: true
  }
})
const replyComment = ref({
  threadRoot: null,
  parentCommentId:null,
})

const commentRefs = ref({})
let currentEl = null
const setReplyComment = (threadRoot,parentCommentId) => {
  if(currentEl )
    currentEl.classList.remove('is-reply')
  currentEl = commentRefs.value[parentCommentId]
  currentEl.classList.add('is-reply')
  replyComment.value.parentCommentId = parentCommentId
  replyComment.value.threadRoot = threadRoot
}

const setItemRef= (el, index) =>{
  if(el&&!commentRefs.value[index])
    commentRefs.value[index]=el
}

// khi đổi trang cần phải reset tránh truy cập id tồn tại
// trong commentRefs nhưng không có trong Dom hiện tại
watch(()=> props.comments , () =>{
  commentRefs.value = {}
})
</script>

<style scoped>

.comments-list{
  position: relative;
  display: flex;
  flex-direction: column;
}
.thread-root{
  position: relative;
}
.thread-line {
  position: absolute;
  left: 30px;
  top: 48px;
  height: calc(100% - 48px);
  bottom: 0;
  border-style: solid;
  border-width: 0 0 0 2px;
  border-color: #e0e0e0;
}
.comment-item{
  display: flex;
  flex-direction: row;
  margin: 10px;
}

.comment{
  background: rgba(234, 234, 234, 0.73);
  border-radius: 10px;
  padding: 0.4rem 1rem;
  margin: 0 3px;
}

.comment-header{
  margin-bottom: 2px;
}
.nickname{
  font-weight: 600;
  font-size: 1.1rem;
  margin-bottom: 5px  ;
}
.metadata {
  font-weight: 700;
  display: inline-block;
  margin-left: .5em;
  color: rgba(0, 0, 0, 0.51);
  font-size: 1em;
}

.comment-reply{
  margin-left: 4.57rem  ;
}
.reply{
  background: #8fede8;
}
.avt-l{
  position: relative;
}
.avt-l::before{
  position: absolute;
  content: '';
  top: 0;
  width: 2rem;
  height: 50%;
  right: 0;
  left: -2.43rem;
  bottom: 0;
  border-left: 2px solid #e0e0e0;
  border-bottom: 2px solid #e0e0e0;
  border-radius: 0 0 0 10px;
}
.dividing{
  border-bottom: 1px solid #e0e0e0;
}
.reply-1{
  background: #00a7e0;
}
:deep(.p-avatar){
  flex-shrink: 0;
}

.is-reply {
  border: 2px solid #00a7e0 !important;
  animation: replyPulse 2s infinite;
  position: relative;
  border-radius: 8px;
}

@keyframes replyPulse {
  0%, 100% {
    box-shadow: 0 0 0 0 rgba(0, 167, 224, 0.4);
    border-width: 2px;
  }
  50% {
    box-shadow: 0 0 0 10px rgba(0, 167, 224, 0);
    border-width: 3px;
  }
}
</style>
