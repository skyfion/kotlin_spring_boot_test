<#include "base.ftl">

<#macro page_body>
<div class="container">
    <p>Document edit page</p>
    <form action="/add" method="post">
        <div class="form-group">
            <#if root??>
            <input type="hidden" value="${root}" name="parent" />
        </#if>
        <input type="hidden" value="${(attribute.id)!}" name="id" />
        <label for="name">Name</label>
        <input type="text" name="name" class="form-control" id="name" value="${(attribute.name)!}">
        <label for="type">Type</label>
        <input type="text" name="type" class="form-control" id="type" value="${(attribute.type)!}">
        <label for="index">Index</label>
        <input type="text" name="index" class="form-control" id="index" value="${(attribute.index)!}">
        <label for="content">Content</label>
        <textarea class="form-control" name="content" rows="5" id="content">${(attribute.content)!}</textarea>
</div>

<button type="submit" class="btn btn-default">Save</button>
</form>
</div>

</#macro>

<@display_page/>