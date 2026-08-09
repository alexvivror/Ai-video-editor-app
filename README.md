# Research-to-Video Android App
## Control/Creation Interface for AI Video Processing Pipeline

This repository contains the Android client for the Research-to-Video platform.
The app serves as the control/creation interface while heavy AI processing (LLM, TTS, avatar generation, video rendering) happens on a backend with GPU acceleration.

## Architecture Overview

```
ANDROID APP (Kotlin + Jetpack Compose)
          │
          │ HTTPS / WebSocket
          � ↓
      API SERVER (FastAPI / Python)
          │
  � ┌───────�┴───────�┬───────�┬───────�┐
  � ↓               � ↓       � ↓       � ↓
Research    AI Agents  Projects  Users
Pipeline    / LLM      /         /
          │           │
          └───────────�┼───────�┘
                      � ↓
               JOB QUEUE (Redis + Celery)
                      │
           � ┌──────────�┴──────────�┐
           � ↓                     � ↓
       CPU Jobs             GPU Jobs
       │                     │
PDF/Web/etc.          TTS / Avatar /
                    AI processing
           └───────────┬───────────�┘
                       � ↓
               VIDEO DIRECTOR
                       � ↓
               Timeline JSON
                       � ↓
               Remotion + FFmpeg
                       � ↓
                    MP4 / HLS
                       � ↓
              Object Storage
                       � ↓
               ANDROID APP
```

## Key Features

- **Input Sources**: PDF upload, research URL, YouTube URL, article URL, video upload
- **Configuration**: Video duration, language, creator style, voice, avatar, presentation style, AI creativity
- **Real-time Progress**: WebSocket updates for research, script, presentation, voice, animation, rendering stages
- **Timeline Editor**: Visual timeline for adjusting slide durations, avatar positioning, highlight timing
- **Export Options**: Multiple resolutions, direct sharing to YouTube/Shorts/Reels
- **Project Management**: Save/load projects, templates, version history

## Technology Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose (declarative, matches Material Design 3)
- **Architecture**: MVVM with Hilt dependency injection
- **Networking**: Retrofit + Moshi for HTTP, WebSocketClient for real-time updates
- **Dependency Injection**: Hilt
- **Async Processing**: Kotlin Coroutines + Flow
- **Storage**: Room database (projects/templates), DataStore (preferences), WorkManager (background tasks)
- **UI Libraries**: Accompanist (system UI controller), Coil (image loading), Lottie (animations)
- **Permissions**: Runtime handling for storage, camera, microphone

## Project Structure

```
research_to_video_android/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/researchtovideo/
│   │   │   │   ├── data/                 # Repositories, DAOs, remote data sources
│   │   │   │   │   ├── local/            # Room database
│   │   │   │   │   └── remote/           # API services
│   │   │   │   ├── di/                   # Hilt modules
│   │   │   │   ├── ui/                   # Compose UI, ViewModels, navigation
│   │   │   │   │   ├── home/
│   │   │   │   │   ├── new_video/
│   │   │   │   │   ├── video_setup/
│   │   │   │   │   ├── editor/
│   │   │   │   │   ├── export/
│   │   │   │   │   └── theme/            # Material Theme, typography, colors
│   │   │   │   ├── util/                 # Extensions, constants, utils
│   │   │   │   └── ResearchToVideoApp.kt # Application class
│   │   │   └── res/                      # Resources (strings, colors, themes)
│   │   └── androidTest/                  # Instrumented tests
│   └── build.gradle.kts
├── README.md
���└── settings.gradle.kts
```

## Getting Started

### Prerequisites
- Android Studio Flamingo or later
- JDK 17
- Android SDK 34 (Android 14)

### Setup
1. Clone this repository
2. Open in Android Studio
3. Sync Gradle
4. Run on emulator or physical device (API 21+)

### Configuration
Update `src/main/java/com/example/researchtovideo/di/NetworkModule.kt` with your API server URL:
```kotlin
@Singleton
@Provides
fun provideApiService(
    @Named("baseUrl") baseUrl: String
): ApiService =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ApiService::class.java)
```

## API Endpoints (Backend)

The Android app expects the following API endpoints (see `backend/api_spec.yaml` for full spec):

- `POST /projects` - Create new project
- `GET /projects/{id}` - Get project details
- `POST /projects/{id}/sources` - Add input sources (PDF, URL, etc.)
- `POST /projects/{id}/script` - Generate script from sources
- `POST /projects/{id}/presentation` - Generate presentation outline
- `POST /projects/{id}/voice` - Generate TTS audio with word timestamps
- `POST /projects/{id}/avatar` - Generate avatar video (optional)
- `POST /projects/{id}/render` - Start video rendering job
- `GET /projects/{id}/status` - Get rendering progress via polling or WebSocket
- `GET /projects/{id}/timeline` - Get/edit timeline JSON
- `POST /projects/{id}/timeline` - Update timeline from Android editor
- `GET /projects/{id}/output` - Get final video URL

## UI/UX Guidelines (Per User Requirements)

- **Grid System**: 8px base grid for spacing
- **Border Radius**: 12px on all cards, buttons, inputs
- **Typography**: Inter font only (via Google Fonts in Compose)
- **Color System**: Neutral base + max 2 accent colors
- **Focus States**: Yellow focus rings (WCAG compliant)
- **Layout**: Card-based, whole-card clickable
- **Icons**: Google Material Symbols outlined (no emojis)
- **Touch Targets**: Minimum 48x48dp
- **Motion**: Meaningful transitions with consistent timing

## Development Guidelines

1. **Heavy Processing**: Never run LLM, TTS, avatar generation, or video rendering on Android device
2. **API Calls**: Use Retrofit with timeout and retry policies
3. **Real-time Updates**: Use WebSocket for progress updates (fallback to polling)
4. **Error Handling**: Show user-friendly messages, allow retry
5. **Accessibility**: Follow Material Design accessibility guidelines
6. **Testing**: Write unit tests for ViewModels, UI tests for critical flows

## Next Steps

1. Implement API service interfaces matching your backend
2. Create Compose screens for each main flow (Home → New Video → Setup → Editor → Export)
3. Implement WebSocket client for real-time progress
4. Build timeline editor with drag-and-drop capabilities
5. Add project persistence with Room
6. Implement export/download functionality
7. Add template system for common video types
8. Add sharing integration (YouTube, Instagram, etc.)

## License

MIT License - see LICENSE file for details