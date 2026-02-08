<template>
  <div class="comment-form-container">
    <Form   v-slot="$form"  :resolver="resolver" class="form-container" @submit="onsubmitF" >
       <div class="textarea-wrapper">
         <Textarea
             :auto-resize="true"
             :rows="1"
             class="custom-textarea"
             :placeholder="'Viết bình luân'"
             v-model="form.content"
         ></Textarea>
       </div>
      <div v-if="!props.replyComment.threadRoot" class="dividing"/>
      <div  >
        <div v-if="!props.replyComment.threadRoot" class="flex justify-content-center flex-wrap align-items-center">
          <div  class="input-wrapper">
            <FormField v-slot="$field" name="nickname">
              <font-awesome-icon icon="user"  />
              <InputText  v-model="form.nickname"  type="text"
                          :placeholder="'Nickname (bắt buộc)'"/>
              <Message v-if="$form.nickname?.invalid" severity="error" size="small" variant="simple">
                {{ $form.nickname.error?.message }}</Message>
            </FormField>
          </div>
          <div class="input-wrapper">
            <FormField v-slot="$field" name="email">
              <font-awesome-icon icon="envelope"/>
              <InputText id="on_email" v-model="form.email" name="email"  :placeholder="'Email (bắt buộc)'" />
              <Message v-if="$form.email?.invalid" severity="error" size="small" variant="simple">
                {{$form.email.error?.message}}
              </Message>
            </FormField>
          </div>
          <div class="input-wrapper">

            <font-awesome-icon icon="location-dot"/>
            <InputText id="on_website" name="website" placeholder="https:// (tùy chọn)" />
          </div>
        </div>
        <button class="icon-button" type="submit" >
          <font-awesome-icon icon="location-arrow" class="mr-2" size="2xl" style="color: #00a6ff; "/>
        </button>
      </div>
    </Form>
  </div>
</template>
<script setup>
import { ref, watch} from "vue";
import { zodResolver } from '@primevue/forms/resolvers/zod';
import { z } from 'zod';
import {useToast} from "@/plugins/primevueConfig/primePluginVue.js";
const toast = useToast()
const props = defineProps({
  replyComment: {
    type: Object,
    required: true,
  },
})

const form = ref({
  content: '',
  nickname: '',
  email: '',
  website: '',
  notice: true
})

watch(() => form.value, (newValue, oldValue) => {
}, { deep: true })

const resolver = ref(zodResolver(
    z.object({
      nickname: z.string().min(1, { message: 'Không được để trống Nickname' }),
      email: z.string()
          .regex(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
              { message: 'Email không hợp lệ' })
    })
));
const onsubmitF= (e) =>{
  const fields = e.states
  if(!e.valid){
    if(fields.email.invalid)
      toast.error(fields.email.errors[0].message)
    if(fields.nickname.invalid)
      toast.error(fields.nickname.errors[0].message)
  }
}
</script>
<style scoped>

.comment-form-container{
  background: #eaeaea;
  border: none;
  border-radius: 25px;
  margin-top: -1px;
  font-size: 1em;
  line-height: 1.4285em;
  min-height: 1em;
  position: relative;
  width: 100%;
}
.form-container{
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}
.textarea-wrapper {
  width: 98% ;
  justify-content: center;
  display: flex;
  position: relative;
}
.dividing{
  margin: 0;
  border-bottom: #b3b3b3 1px solid;
  width: 98%;
}
.box-comment{
  max-width: 100% ;
  min-width: 100% ;
}
:deep(.p-textarea){
  width: 100% ;
  border: none;
}
:deep(.p-inputtext),
:deep(.p-textarea){
  background: transparent;
  border-radius: 0!important;
  box-shadow: none;
}
.icon-button{
  position: absolute;
  right: 15px;
  bottom: 6px;
  cursor: pointer;
}
.input-wrapper{
  border-bottom: #505050 1px solid;
  margin-left: 1rem;
}
:deep(.p-inputtext){
  border: none;
  width: 80%;
}

</style>