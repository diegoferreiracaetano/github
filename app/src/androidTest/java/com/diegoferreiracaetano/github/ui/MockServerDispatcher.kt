package com.diegoferreiracaetano.github.ui

import com.diegoferreiracaetano.github.mock.PullMocked
import com.diegoferreiracaetano.github.mock.RepoMocked
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

internal class MockServerDispatcher {

    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            if (request.path.contains("/search/repositories")) {
                return MockResponse().setResponseCode(200).setBody(RepoMocked.REPO_SUCCESS)
            } else if (request.path.contains("repos/")) {
                return MockResponse().setResponseCode(200).setBody(PullMocked.SUCCESS)
            } else {
                return MockResponse().setResponseCode(404)
            }
        }
    }

    internal inner class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(400)
        }
    }
}