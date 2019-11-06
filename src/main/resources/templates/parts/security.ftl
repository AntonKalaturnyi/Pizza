<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
<#--    admin = user.isAdmin()-->
    >
<#else>
    <#assign
    name = "unknown"
    admin = false
    >
</#if>