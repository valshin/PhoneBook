#!/bin/bash
. ./util/bashTools.sh
TOP_PATH=../src/main

JADE_ROOT=$TOP_PATH/jade
JADE_STATIC_PATH=$JADE_ROOT/static
JADE_TEMPLATES_PATH=$JADE_STATIC_PATH/templates

OUT_ROOT_PATH=$TOP_PATH/resources
OUT_STATIC_PATH=$OUT_ROOT_PATH/static
OUT_TEMPLATES_PATH=$OUT_STATIC_PATH/templates

sayOk "Begin compilation"; 

# if jade $JADE_PATH -o $JADE_OUT_PATH --hierarchy -P -E html; then 
# 	sayOk "Compilation JADE_ROOT finished"; 
# else 
# 	sayError "Compilation JADE_ROOT NOT finished";
# fi
if jade $JADE_STATIC_PATH -o $OUT_STATIC_PATH --hierarchy -P -E html; then 
	sayOk "Compilation JADE_STATIC_PATH finished"; 
else 
	sayError "Compilation JADE_STATIC_PATH NOT finished";
fi
# if jade $JADE_TEMPLATES_PATH -o $OUT_TEMPLATES_PATH --hierarchy -P -E html; then 
# 	sayOk "Compilation JADE_TEMPLATES_PATH finished"; 
# else 
# 	sayError "Compilation JADE_TEMPLATES_PATH NOT finished";
# fi
