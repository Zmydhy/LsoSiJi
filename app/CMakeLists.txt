
cmake_minimum_required(VERSION 3.4.1)


file(GLOB my_source_path src/main/cpp/*.cpp)
add_library( test-lib SHARED ${my_source_path})
add_library( native-lib SHARED ${my_source_path} )


find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

target_link_libraries( # Specifies the target library.
                       native-lib

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )