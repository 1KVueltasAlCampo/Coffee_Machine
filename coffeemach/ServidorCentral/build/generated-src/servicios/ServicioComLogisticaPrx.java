//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.6
//
// <auto-generated>
//
// Generated from file `CoffeMach.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package servicios;

public interface ServicioComLogisticaPrx extends com.zeroc.Ice.ObjectPrx
{
    default java.util.List<java.lang.String> asignacionMaquina(int codigoOperador)
    {
        return asignacionMaquina(codigoOperador, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default java.util.List<java.lang.String> asignacionMaquina(int codigoOperador, java.util.Map<String, String> context)
    {
        return _iceI_asignacionMaquinaAsync(codigoOperador, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.util.List<java.lang.String>> asignacionMaquinaAsync(int codigoOperador)
    {
        return _iceI_asignacionMaquinaAsync(codigoOperador, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.util.List<java.lang.String>> asignacionMaquinaAsync(int codigoOperador, java.util.Map<String, String> context)
    {
        return _iceI_asignacionMaquinaAsync(codigoOperador, context, false);
    }

    /**
     * @hidden
     * @param iceP_codigoOperador -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.util.List<java.lang.String>> _iceI_asignacionMaquinaAsync(int iceP_codigoOperador, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.util.List<java.lang.String>> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "asignacionMaquina", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeInt(iceP_codigoOperador);
                 }, istr -> {
                     java.util.List<java.lang.String> ret;
                     ret = StringSeqHelper.read(istr);
                     return ret;
                 });
        return f;
    }

    default java.util.List<java.lang.String> asignacionMaquinasDesabastecidas(int codigoOperador)
    {
        return asignacionMaquinasDesabastecidas(codigoOperador, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default java.util.List<java.lang.String> asignacionMaquinasDesabastecidas(int codigoOperador, java.util.Map<String, String> context)
    {
        return _iceI_asignacionMaquinasDesabastecidasAsync(codigoOperador, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.util.List<java.lang.String>> asignacionMaquinasDesabastecidasAsync(int codigoOperador)
    {
        return _iceI_asignacionMaquinasDesabastecidasAsync(codigoOperador, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.util.List<java.lang.String>> asignacionMaquinasDesabastecidasAsync(int codigoOperador, java.util.Map<String, String> context)
    {
        return _iceI_asignacionMaquinasDesabastecidasAsync(codigoOperador, context, false);
    }

    /**
     * @hidden
     * @param iceP_codigoOperador -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.util.List<java.lang.String>> _iceI_asignacionMaquinasDesabastecidasAsync(int iceP_codigoOperador, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.util.List<java.lang.String>> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "asignacionMaquinasDesabastecidas", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeInt(iceP_codigoOperador);
                 }, istr -> {
                     java.util.List<java.lang.String> ret;
                     ret = StringSeqHelper.read(istr);
                     return ret;
                 });
        return f;
    }

    default boolean inicioSesion(int codigoOperador, String password)
    {
        return inicioSesion(codigoOperador, password, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean inicioSesion(int codigoOperador, String password, java.util.Map<String, String> context)
    {
        return _iceI_inicioSesionAsync(codigoOperador, password, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> inicioSesionAsync(int codigoOperador, String password)
    {
        return _iceI_inicioSesionAsync(codigoOperador, password, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> inicioSesionAsync(int codigoOperador, String password, java.util.Map<String, String> context)
    {
        return _iceI_inicioSesionAsync(codigoOperador, password, context, false);
    }

    /**
     * @hidden
     * @param iceP_codigoOperador -
     * @param iceP_password -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> _iceI_inicioSesionAsync(int iceP_codigoOperador, String iceP_password, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "inicioSesion", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeInt(iceP_codigoOperador);
                     ostr.writeString(iceP_password);
                 }, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    default void receiveAlarms(String[] alarms)
    {
        receiveAlarms(alarms, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void receiveAlarms(String[] alarms, java.util.Map<String, String> context)
    {
        _iceI_receiveAlarmsAsync(alarms, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> receiveAlarmsAsync(String[] alarms)
    {
        return _iceI_receiveAlarmsAsync(alarms, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> receiveAlarmsAsync(String[] alarms, java.util.Map<String, String> context)
    {
        return _iceI_receiveAlarmsAsync(alarms, context, false);
    }

    /**
     * @hidden
     * @param iceP_alarms -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_receiveAlarmsAsync(String[] iceP_alarms, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "receiveAlarms", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeStringSeq(iceP_alarms);
                 }, null);
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ServicioComLogisticaPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), ServicioComLogisticaPrx.class, _ServicioComLogisticaPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ServicioComLogisticaPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), ServicioComLogisticaPrx.class, _ServicioComLogisticaPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ServicioComLogisticaPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), ServicioComLogisticaPrx.class, _ServicioComLogisticaPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ServicioComLogisticaPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), ServicioComLogisticaPrx.class, _ServicioComLogisticaPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static ServicioComLogisticaPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, ServicioComLogisticaPrx.class, _ServicioComLogisticaPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static ServicioComLogisticaPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, ServicioComLogisticaPrx.class, _ServicioComLogisticaPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default ServicioComLogisticaPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (ServicioComLogisticaPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default ServicioComLogisticaPrx ice_adapterId(String newAdapterId)
    {
        return (ServicioComLogisticaPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default ServicioComLogisticaPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (ServicioComLogisticaPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default ServicioComLogisticaPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (ServicioComLogisticaPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default ServicioComLogisticaPrx ice_invocationTimeout(int newTimeout)
    {
        return (ServicioComLogisticaPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default ServicioComLogisticaPrx ice_connectionCached(boolean newCache)
    {
        return (ServicioComLogisticaPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default ServicioComLogisticaPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (ServicioComLogisticaPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default ServicioComLogisticaPrx ice_secure(boolean b)
    {
        return (ServicioComLogisticaPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default ServicioComLogisticaPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (ServicioComLogisticaPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default ServicioComLogisticaPrx ice_preferSecure(boolean b)
    {
        return (ServicioComLogisticaPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default ServicioComLogisticaPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (ServicioComLogisticaPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default ServicioComLogisticaPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (ServicioComLogisticaPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default ServicioComLogisticaPrx ice_collocationOptimized(boolean b)
    {
        return (ServicioComLogisticaPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default ServicioComLogisticaPrx ice_twoway()
    {
        return (ServicioComLogisticaPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default ServicioComLogisticaPrx ice_oneway()
    {
        return (ServicioComLogisticaPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default ServicioComLogisticaPrx ice_batchOneway()
    {
        return (ServicioComLogisticaPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default ServicioComLogisticaPrx ice_datagram()
    {
        return (ServicioComLogisticaPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default ServicioComLogisticaPrx ice_batchDatagram()
    {
        return (ServicioComLogisticaPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default ServicioComLogisticaPrx ice_compress(boolean co)
    {
        return (ServicioComLogisticaPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default ServicioComLogisticaPrx ice_timeout(int t)
    {
        return (ServicioComLogisticaPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default ServicioComLogisticaPrx ice_connectionId(String connectionId)
    {
        return (ServicioComLogisticaPrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default ServicioComLogisticaPrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (ServicioComLogisticaPrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::servicios::ServicioComLogistica";
    }
}
