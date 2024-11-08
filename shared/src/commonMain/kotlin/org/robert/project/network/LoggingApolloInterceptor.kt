package org.robert.project.network

import com.apollographql.apollo.api.ApolloRequest
import com.apollographql.apollo.api.ApolloResponse
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.ApolloInterceptorChain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class LoggingApolloInterceptor: ApolloInterceptor {
    override fun <D : Operation.Data> intercept(request: ApolloRequest<D>, chain: ApolloInterceptorChain): Flow<ApolloResponse<D>> {
        return chain.proceed(request).onEach { response ->
            println("REQUEST")
            println(request.httpMethod?.name)
            println(request.httpHeaders)
            println("Received response for ${request.operation.name()}: ${response.data}")
        }
    }
}