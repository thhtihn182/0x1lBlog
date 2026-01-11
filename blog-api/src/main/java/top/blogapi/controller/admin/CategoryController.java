package top.blogapi.controller.admin;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import top.blogapi.dto.request.category.CategoryQueryRequest;
import top.blogapi.service.impl.orchestration.CategoryOrchestrator;
import top.blogapi.model.vo.Result;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequestMapping("/admin")
public class CategoryController {
    CategoryOrchestrator categoryOrchestrator;

    @GetMapping("/categories")
    public Result<?> categories(@ModelAttribute CategoryQueryRequest request) {
        return Result.ok("Yêu cầu thành công",categoryOrchestrator.getCategoryList(request));
    }

    @DeleteMapping("/category/{id}")
    public Result<?> deleteCategoryById(@PathVariable Long id) {
        return Result.ok(categoryOrchestrator.deleteCategoryById(id));
    }

    @PostMapping("/categories")
    public Result<?> addCategory(@RequestParam(value = "name", required = true) String name) {
        return Result.ok(categoryOrchestrator.createCategory(name));
    }


    @PutMapping("/category/{id}")
    public Result<?> updateCategoryById(@PathVariable Long id,
                                        @RequestParam(value = "name", required = true) String name) {
        return Result.ok(categoryOrchestrator.updateCategory(id, name));
    }

}
