<#include "base.ftl">

<#macro page_body>
<div class="container">
     <a href="/attributes/add?docId=${docId}" role="button" class="btn btn-primary">Add attribute</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>type</th>
            <th>value</th>
            <th>value int</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#if attributes??>
        <#list attributes as atr>
        <tr>
            <td>${atr.id!}</td>
            <td>${atr.name!}</td>
            <td>${atr.type!}</td>
            <td>${atr.value!}</td>
            <td>${atr.valueInt!}</td>
            <td><a href="/attribute/edit?id=${atr.id}" role="button" class="btn-primary">edit</a></td>
            <td><a href="/attribute/delete?id=${atr.id}&docId=${atr.docId}" role="button" class="btn-danger">delete</a></td>
        </tr>
        </#list>
    </#if>
    </tbody>
    </table>
</div>

</#macro>

<@display_page/>