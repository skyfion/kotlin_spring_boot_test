<#include "base.ftl">

<#macro page_body>
<div class="container">
    <p>Document edit page</p>
    <form action="/attribute/save" method="post">
        <div class="form-group">
            <#if docId??>
                <input type="hidden" value="${docId}" name="docId" />
            <#else>
                <input type="hidden" value="${(attribute.docId)!}" name="docId" />
            </#if>
        <input type="hidden" value="${(attribute.id)!}" name="id" />
        <label for="name">Name</label>
        <input type="text" name="name" class="form-control" id="name" value="${(attribute.name)!}">
        <label for="type">Type</label>
        <input type="text" name="type" class="form-control" id="type" value="${(attribute.type)!}">
        <label for="value">Value</label>
        <input type="text" name="value" class="form-control" id="value" value="${(attribute.value)!}">
        <label for="value_int">Value int</label>
        <input type="number" name="valueInt" class="form-control" id="value_int" value="${(attribute.valueInt)!}">
</div>

<button type="submit" class="btn btn-default">Save</button>
</form>
</div>

</#macro>

<@display_page/>