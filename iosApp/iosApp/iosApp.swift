import SwiftUI
import sharedKit

@main
struct iosAppApp: App {
    init() {
        KoinInitKt.koinInitialization()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
