<template>
  <div class="comment-form-container">
    <Form v-slot="$form" :initialValues :resolver>
      <Textarea></Textarea>
      <Divider style="border: 1px solid"/>
      <div class="flex justify-content-around">
        <FloatLabel variant="on">
          <InputText id="on_nickname" name="nickname"/>
          <Message v-if="$form.nickname?.invalid"
                   severity="error" size="small"
                   variant="simple"></Message>
          <label for="on_nickname"><font-awesome-icon icon="user" class="mr-2" />Nickname</label>

        </FloatLabel>
        <FloatLabel variant="on">
            <InputText id="on_email" name="email"/>
            <label for="on_email"><font-awesome-icon icon="envelope" class="mr-2" />Email</label>
          <Message v-if="$form.email?.invalid"
                   severity="error" size="small"
                   variant="simple"></Message>
        </FloatLabel>


        <Button type="submit" label="Bình luận"/>
      </div>
    </Form>
  </div>
</template>
<script setup>
import {ref} from "vue";
import { zodResolver } from '@primevue/forms/resolvers/zod';
import { z } from 'zod';

const resolver = ref(zodResolver(
    z.object({
      nickname: z.string().min(1, { message: 'Username is required via Zod.' }),
      email: z.string()
          .regex(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
              { message: 'Email invalid' })
    })
));
const initialValues = ref({
  email:'',
  nickname:'',
  commentContent:''
})
</script>
<style scoped>

.comment-form-container{
  background: #fcfff5;
  color: #2c662d;
  border: 1px solid #a3c293;
  margin-top: -1px;
  padding: 1em 1.53666666em;
  font-size: 1em;
  line-height: 1.4285em;
  min-height: 1em;
  position: relative;
  justify-content: space-between;
}
.box-comment{
  max-width: 100% ;
  min-width: 100% ;
}
:deep(.p-textarea){
  width: 100%;
}
:deep(.p-inputtext),
:deep(.p-button),
:deep(.p-textarea){
  border-radius: 0!important;
}
</style>